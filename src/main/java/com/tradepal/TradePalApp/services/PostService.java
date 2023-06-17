package com.tradepal.TradePalApp.services;

import com.tradepal.TradePalApp.exception.NotEnoughItemsException;
import com.tradepal.TradePalApp.exception.PostNotActive;
import com.tradepal.TradePalApp.exception.TradeInviteAlreadyAccepted;
import com.tradepal.TradePalApp.model.*;
import com.tradepal.TradePalApp.repository.*;
import com.tradepal.TradePalApp.requests.PostRequest;
import com.tradepal.TradePalApp.responses.PostResponse;
import com.tradepal.TradePalApp.responses.TradeInviteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PostItemRepository postItemRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    TradeInviteRepository tradeInviteRepository;

    @Autowired
    UserItemRepository userItemRepository;

    @Autowired
    InventoryService inventoryService;

    @Autowired
    InventoryRepository inventoryRepository;

    public ResponseEntity<String> createPost(Long userId, String gameName, List<PostRequest.ItemQuantity> offeredItemsId, List<PostRequest.ItemQuantity> wantedItemsId){
        User user = userRepository.getReferenceById(userId);
        Game game = gameRepository.findGameByName(gameName);
        Post post = new Post(user, game);
        postRepository.save(post);
        for (PostRequest.ItemQuantity itemOffer : offeredItemsId){
            Item item = itemRepository.getReferenceById(itemOffer.getId());
            Optional<UserItem> userItem = userItemRepository.findUserItemByItemAndInventory(item, user.getInventory());
            if(userItem.isPresent() && userItem.get().getQuantity() >= itemOffer.getQty()) {
                PostItem postItem = new PostItem(post, item, TradeDirection.OFFERED, itemOffer.getQty());
                postItemRepository.save(postItem);
            }else {
                postRepository.delete(post);
                throw new NotEnoughItemsException("Not Enough Items For Trade");
            }
        }
        for(PostRequest.ItemQuantity itemWant : wantedItemsId){
            Item item = itemRepository.getReferenceById(itemWant.getId());
            PostItem postItem = new PostItem(post, item, TradeDirection.WANTED, itemWant.getQty());
            postItemRepository.save(postItem);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public ResponseEntity<?> getAllPosts(){
        List<Post> posts = postRepository.findAll();
        List<PostResponse> postsResponse = new ArrayList<>();
        for (Post post : posts) {
            postsResponse.add(new PostResponse(post));
        }
        return new ResponseEntity<>(postsResponse,HttpStatus.OK);
    }

    public ResponseEntity<?> getAllActivePosts(){
        List<Post> posts = postRepository.findAll();
        List<PostResponse> postsResponse = new ArrayList<>();
        for (Post post : posts) {
            if(post.isActive()) {
                postsResponse.add(new PostResponse(post));
            }
        }
        return new ResponseEntity<>(postsResponse,HttpStatus.OK);
    }

    public ResponseEntity<PostResponse> getPost(Long postId){
        Post post = postRepository.getReferenceById(postId);
        return new ResponseEntity<>(new PostResponse(post),HttpStatus.OK);
    }


    public ResponseEntity<String> createTradeInvite(Long interestedId, Long postId){
        User interested = userRepository.getReferenceById(interestedId);
        Post post = postRepository.getReferenceById(postId);
        TradeInvite tradeInvite = new TradeInvite(post, interested);
        tradeInviteRepository.save(tradeInvite);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<String> confirmTrade(Long tradeInviteId){
        TradeInvite tradeInvite = tradeInviteRepository.getReferenceById(tradeInviteId);
        if(!tradeInvite.isAccepted()) {
            if(tradeInvite.getPost().isActive()) {
                tradeInvite.setAccepted(true);
                List<UserItem> toDelete = tradeItems(tradeInvite);
                Post post = tradeInvite.getPost();
                post.setActive(false);
                postRepository.save(post);
                tradeInviteRepository.save(tradeInvite);
                userItemRepository.deleteAll(toDelete);
                inventoryRepository.save(tradeInvite.getRequester().getInventory());
                inventoryRepository.save(tradeInvite.getPost().getUser().getInventory());
                checkPostRequirements(tradeInvite.getRequester());
                checkPostRequirements(tradeInvite.getPost().getUser());
                return new ResponseEntity<>(HttpStatus.OK);
            }else throw new PostNotActive("Post Is Not Active");
        }else throw new TradeInviteAlreadyAccepted("Trade Already Accepted");

    }

    public ResponseEntity<?> rejectTrade(Long tradeInviteId){
        TradeInvite tradeInvite = tradeInviteRepository.getReferenceById(tradeInviteId);
        tradeInviteRepository.delete(tradeInvite);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public List<UserItem> tradeItems(TradeInvite tradeInvite){
        Post post = tradeInvite.getPost();
        User postCreator = tradeInvite.getPost().getUser();
        User trader = tradeInvite.getRequester();
        List<PostItem> itemsTraded = post.getTradeItems();
        List<UserItem> toDelete = new ArrayList<>();
        for(PostItem postItem : itemsTraded){
            UserItem item;
            if(postItem.getTradeDirection() == TradeDirection.OFFERED){
                item = transferItem(postItem, trader, postCreator);
            }
            else{
                item = transferItem(postItem, postCreator, trader);
            }
            if(item != null) {
                toDelete.add(item);
            }
        }
        return toDelete;
    }

    public UserItem transferItem(PostItem postItem, User userIn, User userOut){
        inventoryService.itemAddQuantity(userIn.getInventory(), postItem.getItem(), postItem.getQuantity());
        Optional<UserItem> optionalUserItem = userItemRepository.findUserItemByItemAndInventory(postItem.getItem(), userOut.getInventory());
        if(optionalUserItem.isPresent() && optionalUserItem.get().getQuantity() >= postItem.getQuantity()) {
            UserItem itemOut = optionalUserItem.get();
            int quantityDiff = itemOut.getQuantity() - postItem.getQuantity();
            if (quantityDiff <= 0) {
                return itemOut;
            } else {
                itemOut.setQuantity(quantityDiff);
                return null;
            }
        }else throw new NotEnoughItemsException("Not Enough Items For Trade");
    }

    public ResponseEntity<TradeInviteResponse> getTradeInvite(Long tradeId){
        TradeInvite tradeInvite = tradeInviteRepository.getReferenceById(tradeId);
        return new ResponseEntity<>(new TradeInviteResponse(tradeInvite),HttpStatus.OK);
    }

    public ResponseEntity<?> getTradeInvites(Long userId){
        List<TradeInvite> tradeInvites = tradeInviteRepository.getTradeInviteNotifications(userId);
        List<TradeInviteResponse> tradeInviteResponses = new ArrayList<>();
        for(TradeInvite tradeInvite : tradeInvites){
            tradeInviteResponses.add(new TradeInviteResponse(tradeInvite));
        }
        return new ResponseEntity<>(tradeInviteResponses, HttpStatus.OK);
    }


    public void checkPostRequirements(User user){
        List<Post> posts = postRepository.getPostsByUserAndActive(user, true);
        for(Post post : posts){
            List<PostItem> postItems = post.getTradeItems();
            for (PostItem postItem : postItems){
                Optional<UserItem> userItem = userItemRepository.findUserItemByItemAndInventory(postItem.getItem(), user.getInventory());
                if (userItem.isEmpty() || userItem.get().getQuantity() < postItem.getQuantity()){
                    post.setActive(false);
                    postRepository.save(post);
                    break;
                }
            }
        }
    }
}

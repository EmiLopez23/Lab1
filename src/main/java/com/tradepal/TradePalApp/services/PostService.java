package com.tradepal.TradePalApp.services;

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

    public ResponseEntity<String> createPost(Long userId, String gameName, List<PostRequest.ItemQuantity> offeredItemsId, List<PostRequest.ItemQuantity> wantedItemsId){
        User user = userRepository.getReferenceById(userId);
        Game game = gameRepository.findGameByName(gameName);
        Post post = new Post(user, game);
        postRepository.save(post);
        for (PostRequest.ItemQuantity itemOffer : offeredItemsId){
            Item item = itemRepository.getReferenceById(itemOffer.getId());
            PostItem postItem = new PostItem(post, item, TradeDirection.OFFERED, itemOffer.getQty());
            postItemRepository.save(postItem);
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
            tradeInvite.setAccepted(true);
            tradeInviteRepository.save(tradeInvite);
            tradeItems(tradeInvite);
            Post post = tradeInvite.getPost();
            post.setActive(false);
            postRepository.save(post);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> rejectTrade(Long tradeInviteId){
        TradeInvite tradeInvite = tradeInviteRepository.getReferenceById(tradeInviteId);
        tradeInviteRepository.delete(tradeInvite);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void tradeItems(TradeInvite tradeInvite){
        Post post = tradeInvite.getPost();
        User postCreator = tradeInvite.getPost().getUser();
        User trader = tradeInvite.getRequester();
        List<PostItem> itemsTraded = post.getTradeItems();
        for(PostItem postItem : itemsTraded){
            if(postItem.getTradeDirection() == TradeDirection.OFFERED){
                transferItem(postItem, trader, postCreator);
            }
            else{
                transferItem(postItem, postCreator, trader);
            }
        }
    }

    public void transferItem(PostItem postItem, User userIn, User userOut){
        inventoryService.itemAddQuantity(userIn.getInventory(), postItem.getItem(), postItem.getQuantity());
        Optional<UserItem> optionalUserItem = userItemRepository.findUserItemByItemAndInventory(postItem.getItem(), userOut.getInventory());
        if(optionalUserItem.isPresent()) {
            UserItem itemOut = optionalUserItem.get();
            int quantityDiff = itemOut.getQuantity() - postItem.getQuantity();
            if (quantityDiff <= 0) {
                userItemRepository.delete(itemOut);
            } else {
                itemOut.setQuantity(quantityDiff);
                userItemRepository.save(itemOut);
            }
        }
    }

    public ResponseEntity<?> getTradeInvites(Long userId){
        List<TradeInvite> tradeInvites = tradeInviteRepository.getTradeInviteNotifications(userId);
        List<TradeInviteResponse> tradeInviteResponses = new ArrayList<>();
        for(TradeInvite tradeInvite : tradeInvites){
            tradeInviteResponses.add(new TradeInviteResponse(tradeInvite));
        }
        return new ResponseEntity<>(tradeInviteResponses, HttpStatus.OK);
    }
}

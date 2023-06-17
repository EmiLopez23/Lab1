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

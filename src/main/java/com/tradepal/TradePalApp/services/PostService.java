package com.tradepal.TradePalApp.services;

import com.tradepal.TradePalApp.model.*;
import com.tradepal.TradePalApp.repository.*;
import com.tradepal.TradePalApp.requests.PostRequest;
import com.tradepal.TradePalApp.responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}

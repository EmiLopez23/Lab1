package com.tradepal.TradePalApp.services;


import com.tradepal.TradePalApp.exception.NotEnoughItemsException;
import com.tradepal.TradePalApp.exception.PostNotActive;
import com.tradepal.TradePalApp.exception.TradeInviteAlreadyAccepted;
import com.tradepal.TradePalApp.model.*;
import com.tradepal.TradePalApp.repository.*;
import com.tradepal.TradePalApp.requests.ReviewRequest;
import com.tradepal.TradePalApp.responses.TradeInviteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    TradeInviteRepository tradeInviteRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserItemRepository userItemRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;




    public ResponseEntity<String> createTradeInvite(Long interestedId, Long postId){
        User interested = userRepository.getReferenceById(interestedId);
        Post post = postRepository.getReferenceById(postId);
        TradeInvite tradeInvite = new TradeInvite(post, interested);
        tradeInviteRepository.save(tradeInvite);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<String> confirmTrade(Long tradeInviteId, ReviewRequest review){
        TradeInvite tradeInvite = tradeInviteRepository.getReferenceById(tradeInviteId);
        if(tradeInvite.getStatus() == TradeStatus.SENT) {
            if(tradeInvite.getPost().isActive()) {
                List<UserItem> toDelete = tradeItems(tradeInvite);
                Post post = tradeInvite.getPost();
                post.setActive(false);
                postRepository.save(post);
                tradeInvite.setStatus(TradeStatus.ACCEPTED);
                tradeInviteRepository.save(tradeInvite);
                userItemRepository.deleteAll(toDelete);
                inventoryRepository.save(tradeInvite.getRequester().getInventory());
                inventoryRepository.save(tradeInvite.getPost().getUser().getInventory());
                userService.createReview(post.getUser(), tradeInvite.getRequester(), review.getRating(), review.getContent());
                postService.checkPostRequirements(tradeInvite.getRequester());
                postService.checkPostRequirements(tradeInvite.getPost().getUser());
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

    public ResponseEntity<?> completeInvite(Long inviteId, ReviewRequest review){
        TradeInvite tradeInvite = tradeInviteRepository.getReferenceById(inviteId);
        if(tradeInvite.getStatus() != TradeStatus.COMPLETED){
        tradeInvite.setStatus(TradeStatus.COMPLETED);
        tradeInviteRepository.save(tradeInvite);
        userService.createReview(tradeInvite.getRequester(), tradeInvite.getPost().getUser(), review.getRating(), review.getContent());
        return new ResponseEntity<>(HttpStatus.OK);
        }else throw new TradeInviteAlreadyAccepted("TradeInvite already Completed");
    }
}

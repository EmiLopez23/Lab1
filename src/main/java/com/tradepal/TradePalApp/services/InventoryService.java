package com.tradepal.TradePalApp.services;

import com.tradepal.TradePalApp.exception.UserNotFoundException;
import com.tradepal.TradePalApp.model.Inventory;
import com.tradepal.TradePalApp.model.Item;
import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.model.UserItem;
import com.tradepal.TradePalApp.repository.ItemRepository;
import com.tradepal.TradePalApp.repository.UserItemRepository;
import com.tradepal.TradePalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserItemRepository userItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    public ResponseEntity<?> addItemtoUserInventory(Long userId, Long itemId, int quantity){
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        if(optionalUser.isPresent() && optionalItem.isPresent()){
            User user = optionalUser.get();
            Item item = optionalItem.get();
            Inventory inventory = user.getInventory();
            UserItem itemCheck = userItemRepository.getUserItemByItem(item);
            if(itemCheck != null){
                itemCheck.setQuantity(itemCheck.getQuantity() + quantity);
                userItemRepository.save(itemCheck);
            }
            else{
                UserItem userItem = new UserItem(inventory, item, quantity);
                userItemRepository.save(userItem);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else throw new UserNotFoundException("user or item not found");
    }

}

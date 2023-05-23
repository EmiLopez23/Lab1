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
        User user = userRepository.getReferenceById(userId);
        Item item = itemRepository.getReferenceById(itemId);
        Inventory inventory = user.getInventory();
        itemAddQuantity(inventory, item, quantity);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public void itemAddQuantity(Inventory inventory, Item item, int quantity){
        Optional<UserItem> itemCheck = userItemRepository.findUserItemByItemAndInventory(item, inventory);
        UserItem userItem;
        if(itemCheck.isPresent()){
            userItem = itemCheck.get();
            userItem.setQuantity(userItem.getQuantity() + quantity);
        }
        else{
            userItem = new UserItem(inventory, item, quantity);
        }
        userItemRepository.save(userItem);
    }

}

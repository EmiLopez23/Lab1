package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.Inventory;
import com.tradepal.TradePalApp.model.Item;
import com.tradepal.TradePalApp.model.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserItemRepository extends JpaRepository<UserItem, Long> {
    List<UserItem> getUserItemByInventory(Inventory inventory);
    UserItem getUserItemByItem(Item item);
    Optional<UserItem> findUserItemByItemAndInventory(Item item, Inventory inventory);
    UserItem getUserItemByItemAndInventory(Item item, Inventory inventory);
}

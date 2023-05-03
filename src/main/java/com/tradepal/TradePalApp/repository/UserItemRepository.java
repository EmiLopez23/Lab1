package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.Inventory;
import com.tradepal.TradePalApp.model.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserItemRepository extends JpaRepository<UserItem, Long> {
    UserItem getUserItemByInventory(Inventory inventory);
}

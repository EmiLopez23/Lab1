package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
    Item findItemByName(String name);
}

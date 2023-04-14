package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
}

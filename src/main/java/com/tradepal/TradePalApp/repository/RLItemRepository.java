package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.RLItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RLItemRepository extends JpaRepository<RLItem,Long> {
}

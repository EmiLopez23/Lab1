package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.PostItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostItemRepository extends JpaRepository<PostItem, Long> {
}

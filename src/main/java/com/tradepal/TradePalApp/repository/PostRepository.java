package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}

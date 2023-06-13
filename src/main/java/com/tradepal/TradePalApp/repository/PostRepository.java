package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.Post;
import com.tradepal.TradePalApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> getPostsByUserAndActive(User user, boolean active);
}

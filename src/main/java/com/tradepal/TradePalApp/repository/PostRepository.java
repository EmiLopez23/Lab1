package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.Post;
import com.tradepal.TradePalApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.user = :user AND p.active = :active AND p.user.banned = false")
    List<Post> getPostsByUserAndActive(@Param("user") User user, @Param("active") boolean active);
}

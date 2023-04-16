package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.Category;
import com.tradepal.TradePalApp.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("SELECT c FROM Category c WHERE c.game.name = :gameName")
    List<Category> findCategoriesByGameName(@Param("gameName") String gameName);
}

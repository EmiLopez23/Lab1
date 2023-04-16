package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.Category;
import com.tradepal.TradePalApp.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}

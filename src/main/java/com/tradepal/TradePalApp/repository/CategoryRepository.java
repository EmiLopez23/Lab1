package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}

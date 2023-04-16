package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.Category;
import com.tradepal.TradePalApp.model.CategoryValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryValueRepository extends JpaRepository<CategoryValue,Long> {

}

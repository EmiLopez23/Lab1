package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.CategoryValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryValueRepository extends JpaRepository<CategoryValue,Long> {
    List<CategoryValue> findAllByNameIn(List<String> names);
}

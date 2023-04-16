package com.tradepal.TradePalApp.requests;

import com.tradepal.TradePalApp.model.CategoryValue;
import com.tradepal.TradePalApp.model.Game;

import java.util.List;

public class CategoryRequest {

    String categoryName;
    String gameName;
    List<String> categoryValues;

    public CategoryRequest(String categoryName, String gameName, List<String> categoryValues) {
        this.categoryName = categoryName;
        this.gameName = gameName;
        this.categoryValues = categoryValues;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getGameName() {
        return gameName;
    }

    public List<String> getCategoryValues() {
        return categoryValues;
    }
}

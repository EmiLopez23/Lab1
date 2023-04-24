package com.tradepal.TradePalApp.requests;

import java.util.List;

public class GameRequest {

    public static class CategoryRequest{
        String category;
        String values;

        public CategoryRequest(String category, String values) {
            this.category = category;
            this.values = values;
        }

        public String getCategory() {
            return category;
        }

        public String getValues() {
            return values;
        }
    }



    String game;
    List<CategoryRequest> inputValues;

    public GameRequest(String game, List<CategoryRequest> inputValues) {
        this.game = game;
        this.inputValues = inputValues;
    }

    public String getGame() {
        return game;
    }

    public List<CategoryRequest> getInputValues() {
        return inputValues;
    }
}

package com.tradepal.TradePalApp.requests;

import java.util.List;

public class ItemRequest {

    String name;
    String game;
    List<Long> valuesId;

    public ItemRequest(String name, String game, List<Long> valuesId) {
        this.name = name;
        this.game = game;
        this.valuesId = valuesId;
    }


    public String getName() {
        return name;
    }

    public String getGame() {
        return game;
    }

    public List<Long> getValuesId() {
        return valuesId;
    }
}

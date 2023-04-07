package com.tradepal.TradePalApp.model;


import jakarta.persistence.*;

@Entity
public class RLItem extends Item{
    @Transient
    private String game = "Rocket-League";

    public RLItem(String name, String category, String rarity) {
        super(name, category, rarity);
    }

    public RLItem() {

    }

    public String getGame() {
        return game;
    }
}

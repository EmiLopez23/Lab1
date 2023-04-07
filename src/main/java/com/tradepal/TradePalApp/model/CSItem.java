package com.tradepal.TradePalApp.model;

import jakarta.persistence.*;

@Entity
public class CSItem extends Item{
    @Transient
    private final String game = "CS-GO";

    public CSItem(String name, String category, String rarity) {
        super(name, category, rarity);
    }

    public CSItem() {
    }


    public String getGame() {
        return game;
    }

}

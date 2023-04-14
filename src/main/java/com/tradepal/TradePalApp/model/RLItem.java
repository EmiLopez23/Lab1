package com.tradepal.TradePalApp.model;


import jakarta.persistence.*;

@Entity
public class RLItem extends Item{
    @Transient
    private String game = "Rocket-League";

    public RLItem(String name, String category, String rarity) {

    }

    public RLItem() {

    }


}

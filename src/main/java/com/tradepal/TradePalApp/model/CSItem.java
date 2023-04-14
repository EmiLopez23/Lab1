package com.tradepal.TradePalApp.model;

import jakarta.persistence.*;

@Entity
public class CSItem extends Item{
    @Transient
    private final String game = "CS-GO";

    public CSItem(String name, String category, String rarity) {

    }

    public CSItem() {
    }



}

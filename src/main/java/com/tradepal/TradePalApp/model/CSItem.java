package com.tradepal.TradePalApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CSItem extends Item{
    @Id
    @GeneratedValue(generator = "genCsId",strategy = GenerationType.SEQUENCE)
    private Long id;

    public CSItem(String name, String category, String rarity) {
        super(name, category, rarity);
    }

    public CSItem() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

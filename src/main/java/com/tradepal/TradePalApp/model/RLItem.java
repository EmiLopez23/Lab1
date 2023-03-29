package com.tradepal.TradePalApp.model;


import jakarta.persistence.*;

@Entity
public class RLItem extends Item{

    @Id
    @GeneratedValue(generator = "genRLId",strategy = GenerationType.SEQUENCE)
    private Long id;
    private String category;
    private String rarity;

    public RLItem(String name, String category, String rarity) {
        super(name, category, rarity);
    }

    public RLItem() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

package com.tradepal.TradePalApp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(generator = "inventoryGen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany
    private List<Item> itemList;
}

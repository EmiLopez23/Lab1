package com.tradepal.TradePalApp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(generator = "gameGen", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @OneToMany
    private List<Category> categories;

    @OneToMany
    private List<Item> itemList;
}

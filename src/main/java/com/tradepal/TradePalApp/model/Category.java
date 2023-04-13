package com.tradepal.TradePalApp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(generator = "categoryGen", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @ManyToMany
    private List<Value> values;

    @ManyToOne
    private Game game;

    @ManyToMany
    private List<Item> items;

}

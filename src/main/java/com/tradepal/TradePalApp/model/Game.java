package com.tradepal.TradePalApp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Game {
    @Id
    @GeneratedValue(generator = "gameGen", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;


    @ManyToMany
    private List<Category> categories;

    @OneToMany(mappedBy = "game")
    private List<Item> itemList;


    public Game(String name, List<Category> categories, List<Item> itemList) {
        this.name = name;
        this.categories = categories;
        this.itemList = itemList;
    }

    public Game() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
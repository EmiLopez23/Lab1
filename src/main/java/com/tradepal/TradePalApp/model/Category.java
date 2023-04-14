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
    private List<CategoryValue> categoryValues;

    @ManyToOne
    private Game game;

    @ManyToMany
    private List<Item> items;


    public Category(String name, List<CategoryValue> categoryValues, Game game, List<Item> items) {
        this.name = name;
        this.categoryValues = categoryValues;
        this.game = game;
        this.items = items;
    }

    public Category(){

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CategoryValue> getCategoryValues() {
        return categoryValues;
    }

    public void setCategoryValues(List<CategoryValue> categoryValues) {
        this.categoryValues = categoryValues;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}

package com.tradepal.TradePalApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(generator = "categoryGen", strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category",cascade = CascadeType.PERSIST)
    private List<CategoryValue> categoryValues;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonIgnore
    private Game game;


    public Category(String name, List<CategoryValue> categoryValues, Game game) {
        this.name = name;
        this.categoryValues = categoryValues;
        this.game = game;
    }

    public Category(String name, Game game){
        this.name = name;
        this.game = game;
        this.categoryValues = new ArrayList<>();
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

}

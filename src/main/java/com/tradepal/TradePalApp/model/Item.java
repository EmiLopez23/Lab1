package com.tradepal.TradePalApp.model;


import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue(generator = "genId",strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String imgPath;
    @Transient
    MultipartFile img;

    @ManyToOne
    private Game game;

    @ManyToMany
    private List<CategoryValue> categoryValues;


    public Item(String name) {
        this.name = name;
        this.categoryValues = new ArrayList<>();
    }

    public Item(String name, Game game){
        this.name = name;
        this.game = game;
        this.categoryValues = new ArrayList<>();
    }

    public Item() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<CategoryValue> getCategoryValues() {
        return categoryValues;
    }

    public void setCategoryValues(List<CategoryValue> categoryValues) {
        this.categoryValues = categoryValues;
    }
}

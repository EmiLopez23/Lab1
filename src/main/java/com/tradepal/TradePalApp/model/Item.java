package com.tradepal.TradePalApp.model;


import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

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
    private Set<CategoryValue> categoryValues;


    public Item(String name) {
        this.name = name;
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

    public Set<CategoryValue> getCategoryValues() {
        return categoryValues;
    }

    public void setCategoryValues(Set<CategoryValue> categoryValues) {
        this.categoryValues = categoryValues;
    }
}

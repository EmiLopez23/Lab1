package com.tradepal.TradePalApp.model;


import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@MappedSuperclass
public abstract class Item {
    private String name;
    private String category;
    private String rarity;
    private String imgPath;
    @Transient
    MultipartFile img;
    @Id
    @GeneratedValue(generator = "genId",strategy = GenerationType.SEQUENCE)
    private Long id;


    public Item(String name, String category, String rarity) {
        this.name = name;
        this.category = category;
        this.rarity = rarity;
    }

    public Item() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
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
}

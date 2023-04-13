package com.tradepal.TradePalApp.model;


import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

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



    public Item(String name, String category, String rarity) {
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
}

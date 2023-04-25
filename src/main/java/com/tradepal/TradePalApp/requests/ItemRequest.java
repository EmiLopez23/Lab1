package com.tradepal.TradePalApp.requests;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ItemRequest {

    String name;
    String game;
    List<Long> valuesId;
    MultipartFile img;

    public ItemRequest(String name, String game, List<Long> valuesId, MultipartFile img) {
        this.name = name;
        this.game = game;
        this.valuesId = valuesId;
        this.img = img;
    }


    public String getName() {
        return name;
    }

    public String getGame() {
        return game;
    }

    public List<Long> getValuesId() {
        return valuesId;
    }

    public MultipartFile getImg() {
        return img;
    }
}

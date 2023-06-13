package com.tradepal.TradePalApp.responses;

import com.tradepal.TradePalApp.model.Post;
import com.tradepal.TradePalApp.model.PostItem;

import java.util.List;

public class PostResponse {

    Long id;
    String username;
    String gameName;
    boolean active;
    List<PostItem> tradeItems;

    public PostResponse(Post post){
        this.id = post.getId();
        this.username = post.getUser().getUsername();
        this.gameName = post.getGame().getName();
        this.tradeItems = post.getTradeItems();
        this.active = post.isActive();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public List<PostItem> getTradeItems() {
        return tradeItems;
    }

    public void setTradeItems(List<PostItem> tradeItems) {
        this.tradeItems = tradeItems;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}

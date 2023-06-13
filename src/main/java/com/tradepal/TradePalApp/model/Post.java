package com.tradepal.TradePalApp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postgen")
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "post")
    private List<PostItem> tradeItems;

    @ManyToOne
    private Game game;

    private boolean active;

    public Post(User user, Game game) {
        this.user = user;
        this.game = game;
        this.tradeItems = new ArrayList<>();
        this.active = true;
    }

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<PostItem> getTradeItems() {
        return tradeItems;
    }

    public void setTradeItems(List<PostItem> tradeItems) {
        this.tradeItems = tradeItems;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

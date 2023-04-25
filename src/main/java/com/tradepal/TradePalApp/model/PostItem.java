package com.tradepal.TradePalApp.model;

import jakarta.persistence.*;

@Entity
public class PostItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postItemgen")
    private Long id;

    @ManyToOne
    private Post post;

    @ManyToOne
    private Item item;

    private TradeDirection tradeDirection;

    private int quantity;

    public PostItem(Post post, Item item, TradeDirection tradeDirection, int quantity) {
        this.post = post;
        this.item = item;
        this.tradeDirection = tradeDirection;
        this.quantity = quantity;
    }

    public PostItem() {
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public TradeDirection getTradeDirection() {
        return tradeDirection;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
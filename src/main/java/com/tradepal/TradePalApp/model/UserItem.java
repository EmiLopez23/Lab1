package com.tradepal.TradePalApp.model;

import jakarta.persistence.*;

@Entity
public class UserItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "userItemgen")
    private Long id;

    @ManyToOne
    private Game game;

    @ManyToOne
    private Inventory inventory;

    private int quantity;

    public UserItem(Game game, Inventory inventory, int quantity) {
        this.game = game;
        this.inventory = inventory;
        this.quantity = quantity;
    }

    public UserItem(){}

    public Long getId() {
        return id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

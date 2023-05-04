package com.tradepal.TradePalApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class UserItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "userItemgen")
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Inventory inventory;

    private int quantity;

    @ManyToOne
    private Item item;

    public UserItem(Inventory inventory, Item item, int quantity) {
        this.inventory = inventory;
        this.item = item;
        this.quantity = quantity;
    }

    public UserItem(){}

    public Long getId() {
        return id;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

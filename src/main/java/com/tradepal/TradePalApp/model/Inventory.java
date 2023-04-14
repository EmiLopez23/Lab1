package com.tradepal.TradePalApp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(generator = "inventoryGen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "inventory")
    private List<UserItem> itemList;

    @OneToOne
    private User user;

    public Inventory(List<UserItem> itemList, User user) {
        this.itemList = itemList;
        this.user = user;
    }

    public Inventory(){}

    public Long getId() {
        return id;
    }

    public List<UserItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<UserItem> itemList) {
        this.itemList = itemList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

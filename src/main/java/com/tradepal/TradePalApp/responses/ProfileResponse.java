package com.tradepal.TradePalApp.responses;

import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.model.UserItem;

import java.util.List;

public class ProfileResponse {
    private String username;

    private List<UserItem> inventory;

    private List<PostResponse> activePosts;

    private List<TradeInviteResponse> confirmedTrades;

    public ProfileResponse(User user, List<PostResponse> activePosts, List<TradeInviteResponse> confirmedTrades){
        this.username = user.getUsername();
        this.inventory = user.getInventory().getItemList();
        this.activePosts = activePosts;
        this.confirmedTrades = confirmedTrades;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<UserItem> getInventory() {
        return inventory;
    }

    public void setInventory(List<UserItem> inventory) {
        this.inventory = inventory;
    }

    public List<PostResponse> getActivePosts() {
        return activePosts;
    }

    public void setActivePosts(List<PostResponse> activePosts) {
        this.activePosts = activePosts;
    }

    public List<TradeInviteResponse> getConfirmedTrades() {
        return confirmedTrades;
    }

    public void setConfirmedTrades(List<TradeInviteResponse> confirmedTrades) {
        this.confirmedTrades = confirmedTrades;
    }
}

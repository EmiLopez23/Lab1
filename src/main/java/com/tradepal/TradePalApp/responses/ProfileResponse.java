package com.tradepal.TradePalApp.responses;

import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.model.UserItem;

import java.util.List;

public class ProfileResponse {
    private String username;

    private int rating;

    private List<UserItem> inventory;

    private List<PostResponse> activePosts;

    private List<TradeInviteResponse> confirmedTrades;

    private List<CommentResponse> comments;



    public ProfileResponse(User user, int rating, List<PostResponse> activePosts, List<TradeInviteResponse> confirmedTrades, List<CommentResponse> comments){
        this.username = user.getUsername();
        this.rating = rating;
        this.inventory = user.getInventory().getItemList();
        this.activePosts = activePosts;
        this.confirmedTrades = confirmedTrades;
        this.comments = comments;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<CommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponse> comments) {
        this.comments = comments;
    }
}

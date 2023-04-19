package com.tradepal.TradePalApp.requests;

public class UserItemRequest {
    private Long userId;
    private Long itemId;
    private int quantity;

    public UserItemRequest(Long userId, Long itemId, int quantity) {
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }
}

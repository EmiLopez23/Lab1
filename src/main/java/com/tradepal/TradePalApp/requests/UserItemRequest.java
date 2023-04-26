package com.tradepal.TradePalApp.requests;

public class UserItemRequest {
    private Long itemId;
    private int quantity;

    public UserItemRequest(Long userId, Long itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }


    public Long getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }
}

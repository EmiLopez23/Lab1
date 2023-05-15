package com.tradepal.TradePalApp.requests;

import java.util.List;

public class PostRequest {

    private String gameName;
    private List<ItemQuantity> offeredItems;
    private List<ItemQuantity> wantedItems;


    public PostRequest(String gameName, List<ItemQuantity> offeredItems, List<ItemQuantity> wantedItems) {
        this.gameName = gameName;
        this.offeredItems = offeredItems;
        this.wantedItems = wantedItems;
    }

    public static class ItemQuantity{
        private long id;
        private int qty;

        public ItemQuantity(long id, int qty) {
            this.id = id;
            this.qty = qty;
        }

        public long getId() {
            return id;
        }

        public int getQty() {
            return qty;
        }
    }

    public String getGameName() {
        return gameName;
    }

    public List<ItemQuantity> getOfferedItems() {
        return offeredItems;
    }

    public List<ItemQuantity> getWantedItems() {
        return wantedItems;
    }
}

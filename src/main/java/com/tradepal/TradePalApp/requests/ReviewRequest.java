package com.tradepal.TradePalApp.requests;

public class ReviewRequest {
    private int rating;

    private String content;

    public ReviewRequest(int rating, String content) {
        this.rating = rating;
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

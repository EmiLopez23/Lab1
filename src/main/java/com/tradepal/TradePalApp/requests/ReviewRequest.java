package com.tradepal.TradePalApp.requests;

public class ReviewRequest {

    private String subjectUsername;

    private int rating;

    private String content;

    public ReviewRequest(String subjectUsername, int rating, String content) {
        this.subjectUsername = subjectUsername;
        this.rating = rating;
        this.content = content;
    }

    public String getSubjectUsername() {
        return subjectUsername;
    }

    public void setSubjectUsername(String subjectUsername) {
        this.subjectUsername = subjectUsername;
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

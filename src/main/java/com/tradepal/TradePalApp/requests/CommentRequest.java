package com.tradepal.TradePalApp.requests;

public class CommentRequest {

    private String subjectUsername;

    private String content;

    public CommentRequest(String subjectUsername, String content) {
        this.subjectUsername = subjectUsername;
        this.content = content;
    }

    public String getSubjectUsername() {
        return subjectUsername;
    }

    public void setSubjectUsername(String subjectUsername) {
        this.subjectUsername = subjectUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

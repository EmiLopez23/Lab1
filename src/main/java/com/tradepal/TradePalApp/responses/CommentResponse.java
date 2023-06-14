package com.tradepal.TradePalApp.responses;

import com.tradepal.TradePalApp.model.UserComment;

public class CommentResponse {

    private final String commenter;

    private final String content;


    public CommentResponse(UserComment comment){
        commenter = comment.getCommenter().getUsername();
        content = comment.getContent();
    }

    public String getCommenter() {
        return commenter;
    }

    public String getContent() {
        return content;
    }
}

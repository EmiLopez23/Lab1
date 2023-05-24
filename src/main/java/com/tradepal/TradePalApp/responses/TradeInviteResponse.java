package com.tradepal.TradePalApp.responses;

import com.tradepal.TradePalApp.model.TradeInvite;

public class TradeInviteResponse {

    Long tradeId;
    String requesterUsername;
    PostResponse post;



    public TradeInviteResponse(TradeInvite tradeInvite) {
        this.tradeId = tradeInvite.getId();
        this.requesterUsername = tradeInvite.getRequester().getUsername();
        this.post = new PostResponse(tradeInvite.getPost());
    }

    public Long getTradeId() {
        return tradeId;
    }


    public PostResponse getPostResponse() {
        return post;
    }

    public String getRequesterUsername() {
        return requesterUsername;
    }
}

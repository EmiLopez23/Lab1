package com.tradepal.TradePalApp.responses;

import com.tradepal.TradePalApp.model.TradeInvite;

public class TradeInviteResponse {

    Long tradeId;

    boolean accepted;
    String requesterUsername;
    PostResponse post;



    public TradeInviteResponse(TradeInvite tradeInvite) {
        this.tradeId = tradeInvite.getId();
        this.accepted = tradeInvite.isAccepted();
        this.requesterUsername = tradeInvite.getRequester().getUsername();
        this.post = new PostResponse(tradeInvite.getPost());
    }

    public Long getTradeId() {
        return tradeId;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public PostResponse getPostResponse() {
        return post;
    }

    public String getRequesterUsername() {
        return requesterUsername;
    }
}

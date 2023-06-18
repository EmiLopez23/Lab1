package com.tradepal.TradePalApp.responses;

import com.tradepal.TradePalApp.model.TradeInvite;
import com.tradepal.TradePalApp.model.TradeStatus;

public class TradeInviteResponse {

    Long tradeId;

    TradeStatus accepted;
    String requesterUsername;

    Long requesterId;
    PostResponse post;



    public TradeInviteResponse(TradeInvite tradeInvite) {
        this.tradeId = tradeInvite.getId();
        this.accepted = tradeInvite.getStatus();
        this.requesterUsername = tradeInvite.getRequester().getUsername();
        this.requesterId = tradeInvite.getRequester().getId();
        this.post = new PostResponse(tradeInvite.getPost());
    }

    public Long getTradeId() {
        return tradeId;
    }

    public TradeStatus getAccepted() {
        return accepted;
    }

    public PostResponse getPostResponse() {
        return post;
    }

    public String getRequesterUsername() {
        return requesterUsername;
    }

    public Long getRequesterId() {
        return requesterId;
    }
}

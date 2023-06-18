package com.tradepal.TradePalApp.model;

import jakarta.persistence.*;

@Entity
public class TradeInvite {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "offerGen")
    private Long id;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User requester;




    TradeStatus status;

    public TradeInvite(Post post, User requester) {
        this.post = post;
        this.requester = requester;
        this.status = TradeStatus.SENT;
    }

    public TradeInvite() {
    }

    public Post getPost() {
        return post;
    }

    public Long getId() {
        return id;
    }

    public User getRequester() {
        return requester;
    }


    public TradeStatus getStatus() {
        return status;
    }

    public void setStatus(TradeStatus status) {
        this.status = status;
    }
}

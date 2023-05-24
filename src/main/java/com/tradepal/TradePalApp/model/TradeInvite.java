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




    boolean accepted;

    public TradeInvite(Post post, User requester) {
        this.post = post;
        this.requester = requester;
        this.accepted = false;
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


    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}

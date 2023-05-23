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

    @ManyToOne
    private User creator;


    boolean accepted;

    public TradeInvite(Post post, User requester, User creator) {
        this.post = post;
        this.requester = requester;
        this.creator = creator;
        this.accepted = false;
    }

    public TradeInvite() {
    }

    public Post getPost() {
        return post;
    }

    public User getRequester() {
        return requester;
    }

    public User getCreator() {
        return creator;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}

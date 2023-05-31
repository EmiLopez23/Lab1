package com.tradepal.TradePalApp.model;


import jakarta.persistence.*;

@Entity
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatroom")
    private Long id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User recipient;

    public ChatRoom(User sender, User recipient) {
        this.sender = sender;
        this.recipient = recipient;
    }

    public ChatRoom() {
    }


    public Long getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }
}

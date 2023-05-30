package com.tradepal.TradePalApp.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "chatgen")
    private Long id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User recipient;

    private String content;

    private MessageStatus status;

    private Date timeStamp;

    public ChatMessage(User sender, User recipient, String content, Date timeStamp) {
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.timeStamp = timeStamp;
    }

    public ChatMessage() {
    }

    public Long getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public String getContent() {
        return content;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
}

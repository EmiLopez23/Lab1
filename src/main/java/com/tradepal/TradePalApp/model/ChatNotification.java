package com.tradepal.TradePalApp.model;

public class ChatNotification {
    private Long id;
    private Long senderId;
    private String senderUsername;

    public ChatNotification(Long id, Long senderId, String senderUsername) {
        this.id = id;
        this.senderId = senderId;
        this.senderUsername = senderUsername;

    }

    public Long getId() {
        return id;
    }

    public Long getSenderId() {
        return senderId;
    }

    public String getSenderUsername() {
        return senderUsername;
    }
}

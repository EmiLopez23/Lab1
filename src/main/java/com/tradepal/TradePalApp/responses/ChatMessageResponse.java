package com.tradepal.TradePalApp.responses;

import com.tradepal.TradePalApp.model.ChatMessage;

import java.util.Date;

public class ChatMessageResponse {
    String sender;

    Long senderId;
    String receiver;
    String content;
    Date timeStamp;

    public ChatMessageResponse(ChatMessage chatMessage){
        sender = chatMessage.getSender().getUsername();
        senderId = chatMessage.getSender().getId();
        receiver = chatMessage.getReceiver().getUsername();
        content = chatMessage.getContent();
        timeStamp = chatMessage.getTimeStamp();
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public Long getSenderId() {
        return senderId;
    }
}

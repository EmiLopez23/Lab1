package com.tradepal.TradePalApp.controller;

import com.tradepal.TradePalApp.model.ChatMessage;
import com.tradepal.TradePalApp.model.ChatNotification;
import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.responses.ChatMessageResponse;
import com.tradepal.TradePalApp.services.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@CrossOrigin("http://localhost:3000")
public class ChatController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    ChatMessageService chatMessageService;


    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage chatMessage, @Header("senderId") Long senderId, @Header("receiverId") Long receiverId){
        chatMessageService.setUsers(senderId,receiverId,chatMessage);
        chatMessageService.save(chatMessage);
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getReceiver().getUsername(), "queue/messages", new ChatMessageResponse(chatMessage));
        simpMessagingTemplate.convertAndSendToUser(chatMessage.getReceiver().getUsername(), "queue/notifications", new ChatNotification(chatMessage.getId(), chatMessage.getSender().getId(), chatMessage.getSender().getUsername()));

    }

    @GetMapping("/messages/{senderId}/{receiverId}/count")
    public ResponseEntity<Long> countNewMessages(@PathVariable Long senderId, @PathVariable Long receiverId){
        return ResponseEntity.ok(chatMessageService.countNewMessages(senderId,receiverId));
    }

    @GetMapping("/messages/{senderId}/{receiverId}")
    public ResponseEntity<?> findChatMessages(@PathVariable Long senderId, @PathVariable Long receiverId){
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, receiverId));
    }


    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage(@PathVariable Long id){
        return ResponseEntity.ok(chatMessageService.findById(id));
    }

}

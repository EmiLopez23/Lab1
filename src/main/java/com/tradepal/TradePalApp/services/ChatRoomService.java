package com.tradepal.TradePalApp.services;

import com.tradepal.TradePalApp.model.ChatRoom;
import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {
    @Autowired
    ChatRoomRepository chatRoomRepository;

    private Optional<ChatRoom> getChatRoom(User sender, User recipient, boolean createIfNotExists) {
        Optional<ChatRoom> optional = chatRoomRepository.findBySenderAndRecipient(sender, recipient);
        if (optional.isEmpty()) {
            if (!createIfNotExists) {
                return optional;
            } else {
                ChatRoom senderRecipient = new ChatRoom(sender, recipient);
                ChatRoom recipientSender = new ChatRoom(recipient, sender);
                chatRoomRepository.save(senderRecipient);
                chatRoomRepository.save(recipientSender);
                return Optional.of(senderRecipient);
            }
        } else{
            return optional;
        }
    }
}

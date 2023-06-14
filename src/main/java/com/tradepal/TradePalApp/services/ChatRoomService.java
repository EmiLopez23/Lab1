package com.tradepal.TradePalApp.services;

import com.tradepal.TradePalApp.model.ChatRoom;
import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.repository.ChatRoomRepository;
import com.tradepal.TradePalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {
    @Autowired
    ChatRoomRepository chatRoomRepository;

    @Autowired
    UserRepository userRepository;


    public void checkExistsChatRoom(User sender, User receiver){
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findBySenderAndRecipient(sender, receiver);
        if(optionalChatRoom.isEmpty()){
            ChatRoom senderRecipient = new ChatRoom(sender, receiver);
            ChatRoom recipientSender = new ChatRoom(receiver, sender);
            chatRoomRepository.save(senderRecipient);
            chatRoomRepository.save(recipientSender);
        }
    }

    public Optional<ChatRoom> getChatRoom(User sender, User recipient, boolean createIfNotExists) {
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

    public ResponseEntity<?> createChatRoom(Long senderId, Long receiverId){
        User sender = userRepository.getReferenceById(senderId);
        User receiver = userRepository.getReferenceById(receiverId);
        ChatRoom senderRecipient = new ChatRoom(sender, receiver);
        ChatRoom recipientSender = new ChatRoom(receiver, sender);
        chatRoomRepository.save(senderRecipient);
        chatRoomRepository.save(recipientSender);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

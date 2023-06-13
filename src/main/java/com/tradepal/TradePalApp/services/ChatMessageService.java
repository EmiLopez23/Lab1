package com.tradepal.TradePalApp.services;

import com.tradepal.TradePalApp.model.ChatMessage;
import com.tradepal.TradePalApp.model.ChatRoom;
import com.tradepal.TradePalApp.model.MessageStatus;
import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.repository.ChatMessageRepository;
import com.tradepal.TradePalApp.repository.ChatRoomRepository;
import com.tradepal.TradePalApp.repository.UserRepository;
import com.tradepal.TradePalApp.responses.ChatMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    public ChatMessage save(ChatMessage chatMessage){
        chatMessage.setStatus(MessageStatus.RECEIVED);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    public void setUsers(Long senderId, Long receiverId,ChatMessage chatMessage){
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid senderId"));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid receiverId"));

        chatRoomService.checkExistsChatRoom(sender, receiver);
        chatMessage.setSender(sender);
        chatMessage.setReceiver(receiver);
    }

    public long countNewMessages(Long senderId, Long recipientId){
        User sender = userRepository.getReferenceById(senderId);
        User recipient = userRepository.getReferenceById(recipientId);
        return chatMessageRepository.countBySenderAndReceiverAndStatus(sender, recipient, MessageStatus.RECEIVED);
    }

    public List<ChatMessageResponse> findChatMessages(Long senderId, Long recipientId){
        User sender = userRepository.getReferenceById(senderId);
        User recipient = userRepository.getReferenceById(recipientId);

        List<ChatMessage> messages = chatMessageRepository.findByChat(sender, recipient);

        if(messages.size() > 0){
            updateStatuses(sender, recipient, MessageStatus.DELIVERED);
        }

        List<ChatMessageResponse> messageResponses = new ArrayList<>();
        for(ChatMessage message : messages){
            messageResponses.add(new ChatMessageResponse(message));
        }

        return messageResponses;
    }


    public ChatMessage findById(Long id){
        return chatMessageRepository.getReferenceById(id);
    }

    public void updateStatuses(User sender, User recipient, MessageStatus status){
        List<ChatMessage> messages = chatMessageRepository.findBySenderAndReceiver(sender, recipient);
        for (ChatMessage message : messages){
            message.setStatus(status);
            chatMessageRepository.save(message);
        }
    }

    public ResponseEntity<?> getContacts(Long userId){
        User user = userRepository.getReferenceById(userId);
        List<User> contacts = chatRoomRepository.findContacts(user);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
}

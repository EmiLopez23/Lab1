package com.tradepal.TradePalApp.services;

import com.tradepal.TradePalApp.model.ChatMessage;
import com.tradepal.TradePalApp.model.ChatRoom;
import com.tradepal.TradePalApp.model.MessageStatus;
import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.repository.ChatMessageRepository;
import com.tradepal.TradePalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public ChatMessage save(ChatMessage chatMessage){
        chatMessage.setStatus(MessageStatus.RECEIVED);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    public long countNewMessages(Long senderId, Long recipientId){
        User sender = userRepository.getReferenceById(senderId);
        User recipient = userRepository.getReferenceById(recipientId);
        return chatMessageRepository.countBySenderAndReceiverAndStatus(sender, recipient, MessageStatus.RECEIVED);
    }

    public List<ChatMessage> findChatMessages(Long senderId, Long recipientId){
        User sender = userRepository.getReferenceById(senderId);
        User recipient = userRepository.getReferenceById(recipientId);

        List<ChatMessage> messages = chatMessageRepository.findByChat(sender, recipient);

        if(messages.size() > 0){
            updateStatuses(sender, recipient, MessageStatus.DELIVERED);
        }

        return messages;
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
}

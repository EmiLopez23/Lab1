package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.ChatMessage;
import com.tradepal.TradePalApp.model.MessageStatus;
import com.tradepal.TradePalApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    long countBySenderAndReceiverAndStatus(User sender, User recipient, MessageStatus status);


    @Query("SELECT cm FROM ChatMessage cm WHERE (cm.sender = :sender AND cm.receiver = :receiver) OR (cm.sender = :receiver AND cm.receiver = :sender)")
    List<ChatMessage> findByChat(@Param("sender") User sender, @Param("receiver") User receiver);

    List<ChatMessage> findBySenderAndReceiver(User sender, User receiver);



    @Query("SELECT cm.receiver FROM ChatMessage cm WHERE cm.sender = :sender")
    List<User> findReceiverBySender(@Param("sender") User sender);

    @Query("SELECT cm.sender FROM ChatMessage cm WHERE cm.receiver = :receiver")
    List<User> findSenderByReceiver(@Param("receiver") User receiver);
}

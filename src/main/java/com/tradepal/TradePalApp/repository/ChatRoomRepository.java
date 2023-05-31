package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.ChatRoom;
import com.tradepal.TradePalApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findBySenderAndRecipient(User sender, User recipient);
}

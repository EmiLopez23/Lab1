package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.ChatRoom;
import com.tradepal.TradePalApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findBySenderAndRecipient(User sender, User recipient);

    @Query("SELECT cr.recipient FROM ChatRoom cr WHERE cr.sender = :user")
    List<User> findContacts(@Param("user") User user);
}

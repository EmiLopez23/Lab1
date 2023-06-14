package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.model.UserComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCommentRepository extends JpaRepository<UserComment,Long> {

    List<UserComment> findUserCommentsBySubject(User subject);
}

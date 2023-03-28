package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUsernameAndPassword(String username, String password);
    Boolean existsUserByUsername(String username);
    Boolean existsUserByEmail(String email);
}

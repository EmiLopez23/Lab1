package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.model.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRatingRepository extends JpaRepository<UserRating, Long> {


    @Query("SELECT AVG(ur.rating) FROM UserRating ur WHERE ur.subject = :user")
    int getUserRatingAverage(@Param("user") User user);
}

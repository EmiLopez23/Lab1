package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.TradeInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TradeInviteRepository extends JpaRepository<TradeInvite, Long> {

    @Query("SELECT ti FROM TradeInvite ti WHERE ti.post.id = (SELECT p.id FROM Post p WHERE p.user.id = ?1 )")
    List<TradeInvite> getTradeInviteByPostCreator(Long userId);
}

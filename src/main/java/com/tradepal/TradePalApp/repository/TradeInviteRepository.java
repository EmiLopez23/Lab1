package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.TradeInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TradeInviteRepository extends JpaRepository<TradeInvite, Long> {

    @Query("SELECT ti FROM TradeInvite ti WHERE (ti.requester.id = :userId AND ti.status = com.tradepal.TradePalApp.model.TradeStatus.ACCEPTED AND ti.post.active=false ) OR ti.post.id IN (SELECT p.id FROM Post p WHERE p.user.id = :userId AND p.active = true)")
    List<TradeInvite> getTradeInviteNotifications(@Param("userId") Long userId);

    @Query("SELECT ti FROM TradeInvite ti WHERE ti.status <> com.tradepal.TradePalApp.model.TradeStatus.SENT AND (ti.requester.id = :userId OR  ti.post.id IN (SELECT p.id FROM Post p WHERE p.user.id = :userId ))")
    List<TradeInvite> getUserConfirmedTradeInvites(@Param("userId") Long userId);
}

package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.Post;
import com.tradepal.TradePalApp.model.TradeInvite;
import com.tradepal.TradePalApp.model.TradeStatus;
import com.tradepal.TradePalApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TradeInviteRepository extends JpaRepository<TradeInvite, Long> {

    @Query("SELECT ti FROM TradeInvite ti WHERE (ti.requester.id = :userId AND ti.status = 'ACCEPTED' AND ti.post.active=false AND ti.post.user.banned = false) OR ti.post.id IN (SELECT p.id FROM Post p WHERE p.user.id = :userId AND p.active = true AND ti.requester.banned = false)")
    List<TradeInvite> getTradeInviteNotifications(@Param("userId") Long userId);

    @Query("SELECT ti FROM TradeInvite ti WHERE ti.status <> 'SENT' AND (ti.requester.id = :userId OR  ti.post.id IN (SELECT p.id FROM Post p WHERE p.user.id = :userId ))")
    List<TradeInvite> getUserConfirmedTradeInvites(@Param("userId") Long userId);

    boolean existsByRequesterAndPost(User requester, Post post);

    List<TradeInvite> getTradeInviteByRequesterAndAndStatus(User requester, TradeStatus status);
}

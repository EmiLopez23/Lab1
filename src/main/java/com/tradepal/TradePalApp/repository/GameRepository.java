package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game,Long> {
    Game findGameByName(String name);
}

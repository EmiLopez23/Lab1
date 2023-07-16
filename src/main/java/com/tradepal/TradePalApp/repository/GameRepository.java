package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.Category;
import com.tradepal.TradePalApp.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game,Long> {
    Game findGameByName(String name);
    boolean existsByName(String name);
}

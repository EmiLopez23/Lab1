package com.tradepal.TradePalApp.services;

import com.tradepal.TradePalApp.repository.CategoryRepository;
import com.tradepal.TradePalApp.repository.CategoryValueRepository;
import com.tradepal.TradePalApp.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryValueRepository categoryValueRepository;

    public void addGame(String gameName){}

}

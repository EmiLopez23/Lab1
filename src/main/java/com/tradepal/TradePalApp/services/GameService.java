package com.tradepal.TradePalApp.services;

import com.tradepal.TradePalApp.exception.GameAlreadyCreatedException;
import com.tradepal.TradePalApp.model.Category;
import com.tradepal.TradePalApp.model.CategoryValue;
import com.tradepal.TradePalApp.model.Game;
import com.tradepal.TradePalApp.repository.CategoryRepository;
import com.tradepal.TradePalApp.repository.CategoryValueRepository;
import com.tradepal.TradePalApp.repository.GameRepository;
import com.tradepal.TradePalApp.requests.GameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public ResponseEntity<String> addGame(String gameName, List<GameRequest.CategoryRequest> categories){
        boolean gameExists = gameRepository.existsByName(gameName);
        if(!gameExists) {
            Game game = new Game(gameName);
            gameRepository.save(game);
            for (GameRequest.CategoryRequest categoryRequest : categories) {
                Category category = new Category(categoryRequest.getCategory(), game);
                String[] valueNames = categoryRequest.getValues().split(",");
                categoryRepository.save(category);
                for (String valueName : valueNames) {
                    CategoryValue categoryValue = new CategoryValue(valueName, category);
                    categoryValueRepository.save(categoryValue);
                }
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else throw new GameAlreadyCreatedException("Game has already been created");
    }

    public ResponseEntity<List<Category>> getGameCategories(String gameName){
        Game game = gameRepository.findGameByName(gameName);
        return new ResponseEntity<>(game.getCategories(), HttpStatus.OK);
    }

}

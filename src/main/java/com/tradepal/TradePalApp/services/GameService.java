package com.tradepal.TradePalApp.services;

import com.tradepal.TradePalApp.model.Category;
import com.tradepal.TradePalApp.model.CategoryValue;
import com.tradepal.TradePalApp.model.Game;
import com.tradepal.TradePalApp.repository.CategoryRepository;
import com.tradepal.TradePalApp.repository.CategoryValueRepository;
import com.tradepal.TradePalApp.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryValueRepository categoryValueRepository;

    public ResponseEntity<String> addGame(String gameName){
        Game game = new Game(gameName);
        gameRepository.save(game);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    public ResponseEntity<String> addCategoryToGame(String categoryName, String gameName, List<String> values) {
        Game game = gameRepository.findGameByName(gameName);
        Category category = new Category(categoryName, game);
        categoryRepository.save(category);
        List<CategoryValue> categoryValues = new ArrayList<>();
        for(String value : values){
            CategoryValue categoryValue = new CategoryValue(value, category);
            categoryValues.add(categoryValue);
            categoryValueRepository.save(categoryValue);
        }
        category.setCategoryValues(categoryValues);
        //List<Category> gameCategories = game.getCategories();
        game.getCategories().add(category);
        //game.setCategories(gameCategories);
        categoryRepository.save(category);
        gameRepository.save(game);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}

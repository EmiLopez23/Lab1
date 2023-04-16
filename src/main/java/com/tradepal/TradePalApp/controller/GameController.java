package com.tradepal.TradePalApp.controller;

import com.tradepal.TradePalApp.model.Game;
import com.tradepal.TradePalApp.repository.GameRepository;
import com.tradepal.TradePalApp.requests.CategoryRequest;
import com.tradepal.TradePalApp.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/games")
public class GameController {
    @Autowired
    GameRepository gameRepository;
    @Autowired
    GameService gameService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllGames(){
        return new ResponseEntity<>(gameRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addGame(@RequestBody Game game){return gameService.addGame(game.getName()); }

    @PostMapping("/addCat")
    public ResponseEntity<?> addCategoryToGame(@RequestBody CategoryRequest categoryRequest){return gameService.addCategoryToGame(categoryRequest.getCategoryName(), categoryRequest.getGameName(), categoryRequest.getCategoryValues());}


}

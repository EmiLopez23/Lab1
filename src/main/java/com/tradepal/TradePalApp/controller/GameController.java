package com.tradepal.TradePalApp.controller;

import com.tradepal.TradePalApp.model.Category;
import com.tradepal.TradePalApp.repository.GameRepository;
import com.tradepal.TradePalApp.requests.GameRequest;
import com.tradepal.TradePalApp.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add") //cambio
    public ResponseEntity<?> addGame(@RequestBody GameRequest gameRequest){return gameService.addGame(gameRequest.getGame(), gameRequest.getInputValues()); }

    @PostMapping("/getCat")
    public ResponseEntity<List<Category>> getGameCategories(@RequestBody GameRequest gameRequest){return gameService.getGameCategories(gameRequest.getGame());}


}

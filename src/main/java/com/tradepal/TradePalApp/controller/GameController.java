package com.tradepal.TradePalApp.controller;

import com.tradepal.TradePalApp.repository.GameRepository;
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

    @GetMapping("/all")
    public ResponseEntity<?> findAllGames(){
        return new ResponseEntity<>(gameRepository.findAll(), HttpStatus.OK);
    }
}

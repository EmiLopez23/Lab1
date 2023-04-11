package com.tradepal.TradePalApp.controller;

import com.tradepal.TradePalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    UserRepository userRepository;
    
    @GetMapping("/get")
    public ResponseEntity<String> getMessage(){
            return ResponseEntity.ok("Si estas aca es porque estas autorizado");
    }
}

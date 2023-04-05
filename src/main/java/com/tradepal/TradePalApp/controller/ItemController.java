package com.tradepal.TradePalApp.controller;

import com.tradepal.TradePalApp.repository.CSRepository;
import com.tradepal.TradePalApp.repository.RLItemRepository;
import com.tradepal.TradePalApp.repository.UserRepository;
import com.tradepal.TradePalApp.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/inventory")
public class ItemController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemService itemService;

    @PostMapping("item/rocket/add")
    public ResponseEntity<String> addRocketItem(@RequestHeader("Authorization") String token,@RequestParam("name") String name,@RequestParam("category") String category,@RequestParam("rarity") String rarity,@RequestParam("img") MultipartFile img){
        return itemService.addRLItem(name,rarity,category, img);
    }

    @PostMapping("item/cs/add")
    public ResponseEntity<String> addCSItem(@RequestHeader("Authorization") String token,@RequestParam("name") String name,@RequestParam("category") String category,@RequestParam("rarity") String rarity,@RequestParam("img") MultipartFile img){
            return itemService.addCSItem(name, rarity,category, img);
    }
}

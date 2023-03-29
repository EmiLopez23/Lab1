package com.tradepal.TradePalApp.controller;

import com.tradepal.TradePalApp.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
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
    ItemService itemService;

    @PostMapping("item/rocket/add")
    public ResponseEntity<String> addRocketItem(@RequestParam("name") String name,@RequestParam("category") String category,@RequestParam("rarity") String rarity,@RequestParam("img") MultipartFile img){
        return itemService.addRLItem(name,rarity,category, img);
    }

    @PostMapping("item/cs/add")
    public ResponseEntity<String> addCSItem(@RequestParam("name") String name,@RequestParam("category") String category,@RequestParam("rarity") String rarity,@RequestParam("img") MultipartFile img){
        return itemService.addCSItem(name, rarity,category, img);
    }
}

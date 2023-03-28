package com.tradepal.TradePalApp.controller;


import com.tradepal.TradePalApp.model.Item;
import com.tradepal.TradePalApp.repository.ItemRepository;
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

    @PostMapping("item/add")
    public ResponseEntity<String> addItem(@RequestParam("name") String name,@RequestParam("description") String description,@RequestParam("img") MultipartFile img){
        return itemService.addItem(name,description,img);
    }
}

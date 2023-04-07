package com.tradepal.TradePalApp.controller;

import com.tradepal.TradePalApp.model.CSItem;
import com.tradepal.TradePalApp.model.Item;
import com.tradepal.TradePalApp.model.RLItem;
import com.tradepal.TradePalApp.repository.CSRepository;
import com.tradepal.TradePalApp.repository.RLItemRepository;
import com.tradepal.TradePalApp.repository.UserRepository;
import com.tradepal.TradePalApp.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/inventory")
public class ItemController {
    @Autowired
    CSRepository csRepository;
    @Autowired
    RLItemRepository rlRepository;
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


    @GetMapping("all")
    public ResponseEntity<?> getAll(){
        List<Item> allItems = new ArrayList<>();
        List<CSItem> csItems = csRepository.findAll();
        List<RLItem> rlItems = rlRepository.findAll();
        allItems.addAll(csItems);
        allItems.addAll(rlItems);
        return new ResponseEntity<>(allItems,HttpStatus.OK);
    }
}

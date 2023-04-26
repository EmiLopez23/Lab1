package com.tradepal.TradePalApp.controller;

import com.tradepal.TradePalApp.repository.ItemRepository;
import com.tradepal.TradePalApp.requests.ItemRequest;
import com.tradepal.TradePalApp.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/inventory")
public class ItemController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemService itemService;

    /*@PostMapping("item/add")
    public ResponseEntity<String> addItem(@RequestParam("name") String name, @RequestParam("img")MultipartFile img, @RequestParam("game")String gameName){
        //return itemService.addItem(name, img, gameName);
        return null;
    }*/

    @PostMapping("item/add")
    public ResponseEntity<String> addItem(@RequestBody ItemRequest itemRequest){
        return itemService.addItem(itemRequest.getName(),itemRequest.getGame(), itemRequest.getValuesId(),itemRequest.getImg());
    }


    @GetMapping("all")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(itemRepository.findAll(),HttpStatus.OK);
    }
}

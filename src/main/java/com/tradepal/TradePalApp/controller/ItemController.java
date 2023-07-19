package com.tradepal.TradePalApp.controller;

import com.tradepal.TradePalApp.repository.ItemRepository;
import com.tradepal.TradePalApp.requests.ItemRequest;
import com.tradepal.TradePalApp.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/inventory")
public class ItemController {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemService itemService;

    /*@PostMapping("item/add")
    public ResponseEntity<String> addItem(){
        //return itemService.addItem(name, img, gameName);
        return null;
    }*/

    @PostMapping("item/add")
    public ResponseEntity<String> addItem(@RequestParam("name") String name, @RequestParam("img") MultipartFile img, @RequestParam("game")String gameName,@RequestParam("valuesId") List<Long> valuesId){
        return itemService.addItem(name,gameName, valuesId,img);
    }

    @PostMapping("item/addAsJSON")
    public ResponseEntity<String> addItemJSON(@RequestBody ItemRequest itemRequest){
        return itemService.addItemJSON(itemRequest.getName(),itemRequest.getGame(),itemRequest.getValuesId(), itemRequest.getImg());
    }

    @PostMapping(value="item/addImage",consumes={"multipart/form-data"})
    public ResponseEntity<String> addImage(@RequestParam("img") MultipartFile img){
        return itemService.uploadImg(img);
    }

    @GetMapping("all")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(itemRepository.findAll(),HttpStatus.OK);
    }
}

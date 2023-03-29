package com.tradepal.TradePalApp.services;

import com.tradepal.TradePalApp.model.CSItem;
import com.tradepal.TradePalApp.model.RLItem;
import com.tradepal.TradePalApp.repository.CSRepository;
import com.tradepal.TradePalApp.repository.RLItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class ItemService {


    @Autowired
    RLItemRepository rlRepository;

    @Autowired
    CSRepository csRepository;


    public ResponseEntity<String> addRLItem(String name,String rarity,String category, MultipartFile img){
        RLItem newItem = new RLItem(name,category,rarity);
        try{
            byte[] bytesImg = img.getBytes();
            String imgName = img.getOriginalFilename();
            Path filePath = Path.of("src//main//resources//static/images/RL",imgName);
            Files.write(filePath,bytesImg);
            newItem.setImgPath(imgName);

        } catch (IOException e) {
            throw new RuntimeException("Error while loading Image",e);
       }
        rlRepository.save(newItem);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    public ResponseEntity<String> addCSItem(String name,String rarity,String category, MultipartFile img){
        CSItem newItem = new CSItem(name,category,rarity);
        try{
            byte[] bytesImg = img.getBytes();
            String imgName = img.getOriginalFilename();
            Path filePath = Path.of("src//main//resources//static/images/CS",imgName);
            Files.write(filePath,bytesImg);
            newItem.setImgPath(imgName);

        } catch (IOException e) {
            throw new RuntimeException("Error while loading Image",e);
        }
        csRepository.save(newItem);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}

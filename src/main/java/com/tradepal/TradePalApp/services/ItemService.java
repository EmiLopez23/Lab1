package com.tradepal.TradePalApp.services;

import com.tradepal.TradePalApp.model.Item;
import com.tradepal.TradePalApp.repository.ItemRepository;
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
    ItemRepository itemRepository;


    public ResponseEntity<String> addItem(String name,String description, MultipartFile img){
        Item newItem = new Item(name,description);
        try{
            byte[] bytesImg = img.getBytes();
            String imgName = img.getOriginalFilename();
            Path filePath = Path.of("src//main//resources//static/images",imgName);
            Files.write(filePath,bytesImg);
            newItem.setImgPath(imgName);

        } catch (IOException e) {
            throw new RuntimeException("Error while loading Image",e);
        }
        itemRepository.save(newItem);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}

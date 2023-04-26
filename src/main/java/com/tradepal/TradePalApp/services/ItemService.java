package com.tradepal.TradePalApp.services;

import com.tradepal.TradePalApp.model.CategoryValue;
import com.tradepal.TradePalApp.model.Game;
import com.tradepal.TradePalApp.model.Item;
import com.tradepal.TradePalApp.repository.CategoryValueRepository;
import com.tradepal.TradePalApp.repository.GameRepository;
import com.tradepal.TradePalApp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ItemService {


    @Autowired
    ItemRepository itemRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    CategoryValueRepository categoryValueRepository;

    public ResponseEntity<String> addItem(String name, String gameName, List<String> valuesName, MultipartFile img){ //missing MultipartFile img
        Game game = gameRepository.findGameByName(gameName);
        Item newItem = new Item(name, game);
        List<CategoryValue> values = categoryValueRepository.findAllByNameIn(valuesName);
        newItem.getCategoryValues().addAll(values);
        try{
            byte[] bytesImg = img.getBytes();
            String imgName = img.getOriginalFilename();
            Path filePath = Path.of("../resources",imgName);
            Files.write(filePath,bytesImg);
            newItem.setImgPath(imgName);

        } catch (IOException e) {
            throw new RuntimeException("Error while loading Image",e);
        }
        itemRepository.save(newItem);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}

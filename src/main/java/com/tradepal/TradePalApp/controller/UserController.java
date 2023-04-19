package com.tradepal.TradePalApp.controller;

import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.repository.UserRepository;
import com.tradepal.TradePalApp.requests.UserItemRequest;
import com.tradepal.TradePalApp.services.InventoryService;
import com.tradepal.TradePalApp.services.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/all")
    ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/inventory")
    ResponseEntity<?> getUserInventory(HttpServletRequest request){
        Claims claims = (Claims) request.getAttribute("claims");
        String username = claims.getSubject();
        User user = userRepository.findUserByUsername(username);
        return new ResponseEntity<>(user.getInventory().getItemList(),HttpStatus.OK);
    }


    @PostMapping("inventory/addItem")
    ResponseEntity<?> addItemtoInventory(@RequestBody UserItemRequest userItemRequest){
        return inventoryService.addItemtoUserInventory(userItemRequest.getUserId(), userItemRequest.getItemId(), userItemRequest.getQuantity());
    }
}

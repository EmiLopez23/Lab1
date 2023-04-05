package com.tradepal.TradePalApp.controller;


import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:3000")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    ResponseEntity<?> newUser(@RequestBody User user){
        return userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail());
    }

    @PostMapping("/login")
    ResponseEntity<?> logInUser(@RequestBody User user) {
        return userService.userLogin(user.getUsername(),user.getPassword());
    }
}

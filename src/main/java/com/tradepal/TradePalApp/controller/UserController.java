package com.tradepal.TradePalApp.controller;
import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.repository.UserRepository;
import com.tradepal.TradePalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    ResponseEntity<User> newUser(@RequestBody User user){
        return userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail());
    }

    @PostMapping("/login")
    ResponseEntity<User> logInUser(@RequestBody User user) {
        return userService.userLogin(user.getUsername(),user.getPassword());
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

}

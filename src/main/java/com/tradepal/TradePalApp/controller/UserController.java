package com.tradepal.TradePalApp.controller;
import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.repository.UserRepository;
import com.tradepal.TradePalApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

}

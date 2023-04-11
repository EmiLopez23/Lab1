package com.tradepal.TradePalApp.controller;
import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.repository.UserRepository;
import com.tradepal.TradePalApp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }
}

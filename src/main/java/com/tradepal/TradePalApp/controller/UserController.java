package com.tradepal.TradePalApp.controller;


import com.tradepal.TradePalApp.exception.UserNotFoundException;
import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    User newUser(@RequestBody User user){
        User newUser = new User(user.getUsername(),user.getPassword(),user.getEmail());
        return userRepository.save(newUser);
    }

    @PostMapping("/login")
    User logInUser(@RequestBody User user) {
        User userChecker = userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if(userChecker!=null) return userChecker;
        throw new UserNotFoundException("User Not Found");
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }
}

package com.tradepal.TradePalApp.services;
import com.tradepal.TradePalApp.exception.UserNotFoundException;
import com.tradepal.TradePalApp.exception.UserRegisterException;
import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.UUID.randomUUID;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> userLogin(String username, String password){
        User existingUser = userRepository.findUserByUsernameAndPassword(username,password);
        if(existingUser!=null){
            existingUser.setPassword(null);
            existingUser.setToken(randomUUID());
            return new ResponseEntity<>(existingUser,HttpStatus.OK);
        }
        throw new UserNotFoundException("Check Credentials");

    }

    public ResponseEntity<User> registerUser(String username, String password, String email){
        if (userRepository.existsUserByEmail(email)) throw new UserRegisterException("Email Already Exists");
        if(userRepository.existsUserByUsername(username)) throw new UserRegisterException("Username Already Exists");
        User newUser = new User(username,password,email);
        newUser.setToken(randomUUID());
        userRepository.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }



}

package com.tradepal.TradePalApp.services;
import com.tradepal.TradePalApp.Generator.JWTGeneratorToken;
import com.tradepal.TradePalApp.Generator.JWTGeneratorTokenImpl;
import com.tradepal.TradePalApp.exception.UserNotFoundException;
import com.tradepal.TradePalApp.exception.UserRegisterException;
import com.tradepal.TradePalApp.model.Inventory;
import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.repository.InventoryRepository;
import com.tradepal.TradePalApp.repository.UserRepository;
import com.tradepal.TradePalApp.responses.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTGeneratorTokenImpl jwtGenerator;

    @Autowired
    private InventoryRepository inventoryRepository;

    public ResponseEntity<?> userLogin(String username, String password){
        User existingUser = userRepository.findUserByUsernameAndPassword(username,password);
        if(existingUser!=null){
            return new ResponseEntity<>(new AuthResponse(jwtGenerator.generateToken(existingUser), existingUser.getRole(), existingUser.getUsername(), existingUser.getId()), HttpStatus.OK);
        }
        else throw new UserNotFoundException("User Not Found");

    }

    public ResponseEntity<?> registerUser(String username, String password, String email){
        if (userRepository.existsUserByEmail(email)) throw new UserRegisterException("Email Already Exists");
        if(userRepository.existsUserByUsername(username)) throw new UserRegisterException("Username Already Exists");
        User newUser = new User(username,password,email);
        userRepository.save(newUser);
        Inventory newInventory = new Inventory(newUser);
        inventoryRepository.save(newInventory);
        return new ResponseEntity<>(new AuthResponse(jwtGenerator.generateToken(newUser),newUser.getRole(), newUser.getUsername(), newUser.getId()), HttpStatus.OK);
    }
}

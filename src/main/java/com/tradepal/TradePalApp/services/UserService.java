package com.tradepal.TradePalApp.services;
import com.tradepal.TradePalApp.Generator.JWTGeneratorTokenImpl;
import com.tradepal.TradePalApp.exception.UserNotFoundException;
import com.tradepal.TradePalApp.exception.UserRegisterException;
import com.tradepal.TradePalApp.model.Inventory;
import com.tradepal.TradePalApp.model.Post;
import com.tradepal.TradePalApp.model.TradeInvite;
import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.repository.InventoryRepository;
import com.tradepal.TradePalApp.repository.PostRepository;
import com.tradepal.TradePalApp.repository.TradeInviteRepository;
import com.tradepal.TradePalApp.repository.UserRepository;
import com.tradepal.TradePalApp.responses.AuthResponse;
import com.tradepal.TradePalApp.responses.PostResponse;
import com.tradepal.TradePalApp.responses.ProfileResponse;
import com.tradepal.TradePalApp.responses.TradeInviteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TradeInviteRepository tradeInviteRepository;
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

    public ResponseEntity<?> getProfile(String username){
        User user = userRepository.findUserByUsername(username);
        List<Post> posts = postRepository.getPostsByUserAndActive(user, true);
        List<TradeInvite> tradeInvites = tradeInviteRepository.getUserConfirmedTradeInvites(user.getId(), true);
        List<PostResponse> activePosts = new ArrayList<>();
        List<TradeInviteResponse> confirmedTrades = new ArrayList<>();
        for(Post post : posts){
            activePosts.add(new PostResponse(post));
        }
        for(TradeInvite tradeInvite : tradeInvites){
            confirmedTrades.add(new TradeInviteResponse(tradeInvite));
        }
        ProfileResponse profileResponse = new ProfileResponse(user, activePosts, confirmedTrades);
        return new ResponseEntity<>(profileResponse, HttpStatus.OK);
    }
}

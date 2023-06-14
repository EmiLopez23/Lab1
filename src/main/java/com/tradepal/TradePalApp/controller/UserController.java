package com.tradepal.TradePalApp.controller;

import com.tradepal.TradePalApp.model.Report;
import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.repository.UserRepository;
import com.tradepal.TradePalApp.requests.CommentRequest;
import com.tradepal.TradePalApp.requests.ReportRequest;
import com.tradepal.TradePalApp.requests.ReviewRequest;
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
    ResponseEntity<?> addItemtoInventory(HttpServletRequest request, @RequestBody UserItemRequest userItemRequest){
        Claims claims = (Claims) request.getAttribute("claims");
        Long userId = Long.parseLong(claims.get("id").toString());
        return inventoryService.addItemtoUserInventory(userId, userItemRequest.getItemId(), userItemRequest.getQuantity());
    }

    @GetMapping("/{username}")
    ResponseEntity <?> getUserProfile(@PathVariable String username){
        return userService.getProfile(username);
    }

    @PostMapping("/report")
    ResponseEntity<?> reportUser(HttpServletRequest request, @RequestBody ReportRequest report){
        Claims claims = (Claims) request.getAttribute("claims");
        Long userId = Long.parseLong(claims.get("id").toString());
        return userService.createReport(userId, report.getSubjectUsername(), report.getContent());
    }

    @PostMapping("/rate")
    ResponseEntity<?> rateUser(HttpServletRequest request, @RequestParam("user") String username, @RequestParam("rating") int rating){
        Claims claims = (Claims) request.getAttribute("claims");
        Long userId = Long.parseLong(claims.get("id").toString());
        return userService.createRating(userId, username, rating);
    }

    @PostMapping("/comment")
    ResponseEntity<?> commentUser(HttpServletRequest request, @RequestBody CommentRequest comment){
        Claims claims = (Claims) request.getAttribute("claims");
        Long userId = Long.parseLong(claims.get("id").toString());
        return userService.createComment(userId, comment.getSubjectUsername(), comment.getContent());
    }

    @PostMapping("/review")
    ResponseEntity<?> reviewUser(HttpServletRequest request, @RequestBody ReviewRequest review){
        Claims claims = (Claims) request.getAttribute("claims");
        Long userId = Long.parseLong(claims.get("id").toString());
        return userService.createReview(userId, review.getSubjectUsername(), review.getRating(), review.getContent());
    }

}

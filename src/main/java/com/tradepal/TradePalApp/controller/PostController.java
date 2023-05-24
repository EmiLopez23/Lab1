package com.tradepal.TradePalApp.controller;

import com.tradepal.TradePalApp.repository.PostRepository;
import com.tradepal.TradePalApp.repository.TradeInviteRepository;
import com.tradepal.TradePalApp.repository.UserRepository;
import com.tradepal.TradePalApp.requests.PostRequest;
import com.tradepal.TradePalApp.services.PostService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/post")
public class PostController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    TradeInviteRepository tradeInviteRepository;
    
    @GetMapping("/get")
    public ResponseEntity<String> getMessage(){
            return ResponseEntity.ok("Si estas aca es porque estas autorizado");
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllPosts(){
        return postService.getAllPosts();
    }

    @PostMapping("/create-post")
    public ResponseEntity<?> createPost(HttpServletRequest request, @RequestBody PostRequest postRequest){
        Claims claims = (Claims) request.getAttribute("claims");
        Long userId = Long.parseLong(claims.get("id").toString());
        return postService.createPost(userId, postRequest.getGameName(), postRequest.getOfferedItems(), postRequest.getWantedItems());
    }

    @PostMapping("/create-invite")
    public ResponseEntity<String> createInvite(HttpServletRequest request, @RequestParam("postId") Long postId){
        Claims claims = (Claims) request.getAttribute("claims");
        Long userId = Long.parseLong(claims.get("id").toString());
        return postService.createTradeInvite(userId, postId);
    }

    @PostMapping("/accept-invite")
    public ResponseEntity<String> acceptInvite(@RequestParam("inviteId") Long inviteId){
        return postService.confirmTrade(inviteId);
    }


    @GetMapping("/all-invites")
    public ResponseEntity<?> getMyInvites(HttpServletRequest request){
        Claims claims = (Claims) request.getAttribute("claims");
        Long userId = Long.parseLong(claims.get("id").toString());
        return postService.getTradeInvites(userId);
    }
}

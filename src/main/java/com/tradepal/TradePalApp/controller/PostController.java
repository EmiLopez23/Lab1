package com.tradepal.TradePalApp.controller;

import com.tradepal.TradePalApp.repository.PostRepository;
import com.tradepal.TradePalApp.repository.UserRepository;
import com.tradepal.TradePalApp.requests.PostRequest;
import com.tradepal.TradePalApp.responses.TradeInviteResponse;
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
    


    @GetMapping("/all")
    public ResponseEntity<?> findAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/allActive")
    public ResponseEntity<?> findAllActivePosts(){
        return postService.getAllActivePosts();
    }

    @PostMapping("/create-post")
    public ResponseEntity<?> createPost(HttpServletRequest request, @RequestBody PostRequest postRequest){
        Claims claims = (Claims) request.getAttribute("claims");
        Long userId = Long.parseLong(claims.get("id").toString());
        return postService.createPost(userId, postRequest.getGameName(), postRequest.getOfferedItems(), postRequest.getWantedItems());
    }

    @PostMapping("/create-invite/{postId}")
    public ResponseEntity<String> createInvite(HttpServletRequest request, @PathVariable Long postId){
        Claims claims = (Claims) request.getAttribute("claims");
        Long userId = Long.parseLong(claims.get("id").toString());
        return postService.createTradeInvite(userId, postId);
    }

    @PostMapping("/accept-invite/{inviteId}")
    public ResponseEntity<String> acceptInvite(@PathVariable Long inviteId){
        return postService.confirmTrade(inviteId);
    }

    @PostMapping("/reject-invite/{inviteId}")
    public ResponseEntity<?> rejectInvite(@PathVariable Long inviteId){
        return postService.rejectTrade(inviteId);
    }


    @GetMapping("/all-invites")
    public ResponseEntity<?> getInvitesByCreatorId(HttpServletRequest request){
        Claims claims = (Claims) request.getAttribute("claims");
        Long userId = Long.parseLong(claims.get("id").toString());
        return postService.getTradeInvites(userId);
    }

    @GetMapping("/getInvite/{inviteId}")
    public ResponseEntity<TradeInviteResponse> getPost(@PathVariable Long inviteId){
        return postService.getTradeInvite(inviteId);
    }

}

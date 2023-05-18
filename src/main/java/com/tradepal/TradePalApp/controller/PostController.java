package com.tradepal.TradePalApp.controller;

import com.tradepal.TradePalApp.repository.PostRepository;
import com.tradepal.TradePalApp.repository.UserRepository;
import com.tradepal.TradePalApp.requests.PostRequest;
import com.tradepal.TradePalApp.services.PostService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    
    @GetMapping("/get")
    public ResponseEntity<String> getMessage(){
            return ResponseEntity.ok("Si estas aca es porque estas autorizado");
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllPosts(){
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create-post")
    public ResponseEntity<?> createPost(HttpServletRequest request, @RequestBody PostRequest postRequest){
        Claims claims = (Claims) request.getAttribute("claims");
        Long userId = Long.parseLong(claims.get("id").toString());
        return postService.createPost(userId, postRequest.getGameName(), postRequest.getOfferedItems(), postRequest.getWantedItems());
    }
}

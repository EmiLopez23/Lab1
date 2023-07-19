package com.tradepal.TradePalApp.services;
import com.tradepal.TradePalApp.Generator.JWTGeneratorTokenImpl;
import com.tradepal.TradePalApp.exception.UserBannedException;
import com.tradepal.TradePalApp.exception.UserNotFoundException;
import com.tradepal.TradePalApp.exception.UserRegisterException;
import com.tradepal.TradePalApp.model.*;
import com.tradepal.TradePalApp.repository.*;
import com.tradepal.TradePalApp.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private TradeInviteRepository tradeInviteRepository;
    @Autowired
    private JWTGeneratorTokenImpl jwtGenerator;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private UserRatingRepository userRatingRepository;
    @Autowired
    private UserCommentRepository userCommentRepository;
    @Autowired
    private MailService mailService;

    public ResponseEntity<?> userLogin(String username, String password){
        User existingUser = userRepository.findUserByUsernameAndPassword(username,password);
        if(existingUser!=null){
            if(!existingUser.isBanned()) {
                return new ResponseEntity<>(new AuthResponse(jwtGenerator.generateToken(existingUser), existingUser.getRole(), existingUser.getUsername(), existingUser.getId()), HttpStatus.OK);
            }
            else throw new UserBannedException("User Has Been Banned");
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
        mailService.sendRegisterMail(email, username);
        return new ResponseEntity<>(new AuthResponse(jwtGenerator.generateToken(newUser),newUser.getRole(), newUser.getUsername(), newUser.getId()), HttpStatus.OK);
    }

    public ResponseEntity<?> getProfile(String username) {
        User user = userRepository.findUserByUsername(username);
        Optional<Integer> ratingAvg = userRatingRepository.getUserRatingAverage(user);
        int rating;
        if(ratingAvg.isEmpty()){
            rating = 0;
        }else {
            rating = ratingAvg.get();
        }
        List<Post> posts = postRepository.getPostsByUserAndActive(user, true);
        List<TradeInvite> tradeInvites = tradeInviteRepository.getUserConfirmedTradeInvites(user.getId());
        List<UserComment> userComments = userCommentRepository.findUserCommentsBySubject(user);
        List<PostResponse> activePosts = new ArrayList<>();
        List<TradeInviteResponse> confirmedTrades = new ArrayList<>();
        List<CommentResponse> comments = new ArrayList<>();
        for(Post post : posts){
            activePosts.add(new PostResponse(post));
        }
        for(TradeInvite tradeInvite : tradeInvites){
            confirmedTrades.add(new TradeInviteResponse(tradeInvite));
        }
        for(UserComment comment : userComments){
            comments.add(new CommentResponse(comment));
        }
        ProfileResponse profileResponse = new ProfileResponse(user, rating, activePosts, confirmedTrades, comments);
        return new ResponseEntity<>(profileResponse, HttpStatus.OK);
    }

    public ResponseEntity<?> createReport(Long reporterId, String subjectUsername, String content, List<MultipartFile> files){
        User reporter = userRepository.getReferenceById(reporterId);
        User subject = userRepository.findUserByUsername(subjectUsername);
        Report report = new Report(reporter, subject, content);
        try {
            List<String> paths = new ArrayList<>();
            for(MultipartFile file : files){
                byte[] bytesFile = file.getBytes();
                String fileName = file.getOriginalFilename();
                Path filePath = Path.of("../resources", fileName);
                Files.write(filePath, bytesFile);
                paths.add(fileName);
            }
            report.setFilePath(paths);
        } catch (IOException e){
            throw new RuntimeException("Error while loading file", e);
        }

        reportRepository.save(report);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    public ResponseEntity<?> createRating(Long raterId, String ratedUsername, int rating){
        User rater = userRepository.getReferenceById(raterId);
        User rated = userRepository.findUserByUsername(ratedUsername);
        UserRating userRating = new UserRating(rater, rated, rating);
        userRatingRepository.save(userRating);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> createComment(Long commenterId, String commentedUsername, String content){
        User commenter = userRepository.getReferenceById(commenterId);
        User commented = userRepository.findUserByUsername(commentedUsername);
        UserComment comment = new UserComment(commenter, commented, content);
        userCommentRepository.save(comment);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public void createReview(User reviewer, User subject, int rating, String content){
        UserRating userRating = new UserRating(reviewer, subject, rating);
        UserComment comment = new UserComment(reviewer, subject, content);
        userRatingRepository.save(userRating);
        userCommentRepository.save(comment);
    }
}

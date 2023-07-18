package com.tradepal.TradePalApp.controller;

import com.tradepal.TradePalApp.services.AdminService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/reports")
    ResponseEntity<?> getReports(){return adminService.getReports();}

    @PostMapping("report/{reportId}")
    ResponseEntity<?> answerReport(HttpServletRequest request, @PathVariable Long reportId, @RequestParam("ban") boolean response){
        Claims claims = (Claims) request.getAttribute("claims");
        Long userId = Long.parseLong(claims.get("id").toString());
        return adminService.handleReport(userId, response, reportId);
    }

}

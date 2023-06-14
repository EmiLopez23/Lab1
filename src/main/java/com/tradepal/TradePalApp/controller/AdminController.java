package com.tradepal.TradePalApp.controller;

import com.tradepal.TradePalApp.services.AdminService;
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
    ResponseEntity<?> answerReport(@PathVariable Long reportId, @RequestParam("ban") boolean response){
        return adminService.handleReport(response, reportId);
    }

}

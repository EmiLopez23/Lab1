package com.tradepal.TradePalApp.services;


import com.tradepal.TradePalApp.model.Report;
import com.tradepal.TradePalApp.model.User;
import com.tradepal.TradePalApp.repository.ReportRepository;
import com.tradepal.TradePalApp.repository.UserRepository;
import com.tradepal.TradePalApp.responses.ReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReportRepository reportRepository;

    public ResponseEntity<?> handleReport(boolean response,Long reportId){
        Report report = reportRepository.getReferenceById(reportId);
        if(response){
            User user = report.getSubject();
            user.setBanned(true);
            userRepository.save(user);
        }
        report.setResolved(true);
        reportRepository.save(report);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> getReports(){
        List<Report> reports = reportRepository.getPendingReports();
        List<ReportResponse> pending = new ArrayList<>();

        for(Report report : reports){
            pending.add(new ReportResponse(report));
        }

        return new ResponseEntity<>(pending, HttpStatus.OK);
    }


}

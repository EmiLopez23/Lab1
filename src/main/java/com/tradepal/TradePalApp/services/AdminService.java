package com.tradepal.TradePalApp.services;


import com.tradepal.TradePalApp.exception.UserNotAdminException;
import com.tradepal.TradePalApp.model.Report;
import com.tradepal.TradePalApp.model.Role;
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

    public ResponseEntity<?> handleReport(Long adminId, boolean response,Long reportId){
        User admin = userRepository.getReferenceById(adminId);
        if(admin.getRole() == Role.ADMIN) {
            Report report = reportRepository.getReferenceById(reportId);
            if (response) {
                User user = report.getSubject();
                user.setBanned(true);
                userRepository.save(user);
                List<Report> reports = reportRepository.getReportsBySubject(user);
                reports.forEach(report1 -> report1.setResolved(true));
                reportRepository.saveAll(reports);
            }else {
                report.setResolved(true);
                reportRepository.save(report);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } else throw new UserNotAdminException("User is not an Admin");
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

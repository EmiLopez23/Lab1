package com.tradepal.TradePalApp.repository;

import com.tradepal.TradePalApp.model.Report;
import com.tradepal.TradePalApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query("select r from Report r where r.resolved = false and r.subject.banned = false")
    List<Report> getPendingReports();

    @Query("select r from Report r")
    List<Report> getReportsBySubject(User subject);
}

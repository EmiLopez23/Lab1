package com.tradepal.TradePalApp.responses;

import com.tradepal.TradePalApp.model.Report;

import java.util.List;

public class ReportResponse {
    String subjectUsername;
    Long subjectId;
    String reporterUsername;
    Long reporterId;
    Long reportId;
    String content;

    List<String> filePaths;

    public ReportResponse(Report report){
        this.reporterUsername = report.getReporter().getUsername();
        this.reporterId = report.getReporter().getId();
        this.subjectUsername = report.getSubject().getUsername();
        this.subjectId = report.getSubject().getId();
        this.reportId = report.getId();
        this.content = report.getContent();
        this.filePaths = report.getFilePath();
    }

    public String getSubjectUsername() {
        return subjectUsername;
    }

    public void setSubjectUsername(String subjectUsername) {
        this.subjectUsername = subjectUsername;
    }

    public String getReporterUsername() {
        return reporterUsername;
    }

    public void setReporterUsername(String reporterUsername) {
        this.reporterUsername = reporterUsername;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getReporterId() {
        return reporterId;
    }

    public void setReporterId(Long reporterId) {
        this.reporterId = reporterId;
    }

    public List<String> getFilePaths() {
        return filePaths;
    }

    public void setFilePaths(List<String> filePaths) {
        this.filePaths = filePaths;
    }
}

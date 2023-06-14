package com.tradepal.TradePalApp.responses;

import com.tradepal.TradePalApp.model.Report;

public class ReportResponse {
    String subjectUsername;
    String reporterUsername;
    Long reportId;
    String content;

    public ReportResponse(Report report){
        this.reporterUsername = report.getReporter().getUsername();
        this.subjectUsername = report.getReporter().getUsername();
        this.reportId = report.getId();
        this.content = report.getContent();
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
}

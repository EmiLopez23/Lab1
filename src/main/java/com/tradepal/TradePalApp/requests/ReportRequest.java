package com.tradepal.TradePalApp.requests;

public class ReportRequest {
    Long reporterId;
    String subjectUsername;
    String content;

    public ReportRequest(Long reporterId,String subjectUsername, String content) {
        this.subjectUsername = subjectUsername;
        this.content = content;
        this.reporterId = reporterId;
    }

    public String getSubjectUsername() {
        return subjectUsername;
    }

    public void setSubjectUsername(String subjectUsername) {
        this.subjectUsername = subjectUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getReporterId() {
        return reporterId;
    }

    public void setReporterId(Long reporterId) {
        this.reporterId = reporterId;
    }
}

package com.tradepal.TradePalApp.requests;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ReportRequest {
    Long reporterId;
    String subjectUsername;
    String content;

    List<MultipartFile> files;

    public ReportRequest(Long reporterId,String subjectUsername, String content, List<MultipartFile> files) {
        this.subjectUsername = subjectUsername;
        this.content = content;
        this.reporterId = reporterId;
        this.files = files;
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

    public List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
}

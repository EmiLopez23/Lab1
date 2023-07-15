package com.tradepal.TradePalApp.model;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reportId")
    private Long id;

    @ManyToOne
    private User reporter;
    @ManyToOne
    private User subject;
    private String content;
    private boolean resolved;

    @ElementCollection
    private List<String> filePath;
    @Transient
    private List<MultipartFile> evidence;



    public Report(User reporter, User subject, String content) {
        this.reporter = reporter;
        this.subject = subject;
        this.content = content;
        this.resolved = false;
        this.evidence = new ArrayList<>();
        this.filePath = new ArrayList<>();
    }

    public Report() {

    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public User getSubject() {
        return subject;
    }

    public void setSubject(User subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isResolved() {
        return resolved;
    }

    public void setResolved(boolean resolved) {
        this.resolved = resolved;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<MultipartFile> getEvidence() {
        return evidence;
    }

    public void setEvidence(List<MultipartFile> evidence) {
        this.evidence = evidence;
    }

    public List<String> getFilePath() {
        return filePath;
    }

    public void setFilePath(List<String> filePath) {
        this.filePath = filePath;
    }
}

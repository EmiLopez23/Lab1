package com.tradepal.TradePalApp.model;

import jakarta.persistence.*;
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

    public Report(User reporter, User subject, String content) {
        this.reporter = reporter;
        this.subject = subject;
        this.content = content;
        this.resolved = false;
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
}

package com.tradepal.TradePalApp.model;


import jakarta.persistence.*;

@Entity
public class UserComment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commentgen")
    private Long id;

    @ManyToOne
    private User commenter;

    @ManyToOne
    private User subject;

    private String content;


    public UserComment(User commenter, User subject, String content) {
        this.commenter = commenter;
        this.subject = subject;
        this.content = content;
    }

    public UserComment(){

    }

    public Long getId() {
        return id;
    }

    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }

    public User getSubject() {
        return subject;
    }

    public void setSubject(User commented) {
        this.subject = commented;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

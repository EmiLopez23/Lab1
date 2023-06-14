package com.tradepal.TradePalApp.model;

import jakarta.persistence.*;

@Entity
public class UserRating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ratinggen")
    private Long id;

    @ManyToOne
    private User rater;

    @ManyToOne
    private User subject;

    private int rating;

    public UserRating(User rater, User subject, int rating) {
        this.rater = rater;
        this.subject = subject;
        this.rating = rating;
    }


    public UserRating() {
    }

    public Long getId() {
        return id;
    }

    public User getRater() {
        return rater;
    }

    public void setRater(User rater) {
        this.rater = rater;
    }

    public User getSubject() {
        return subject;
    }

    public void setSubject(User rated) {
        this.subject = rated;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

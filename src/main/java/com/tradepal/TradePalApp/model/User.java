package com.tradepal.TradePalApp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(generator = "userIdGen",strategy = GenerationType.SEQUENCE)
    private Long id;

    private String username;
    private String password;
    private String email;

    private Integer token;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.generateHashCode();
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getToken() {
        return token;
    }

    private Integer generateHashCode(){
        if (this.password !=null && this.username !=null && this.email !=null){
            this.token = username.hashCode() * email.hashCode() * password.hashCode();
        }
        return 0;
    }
}

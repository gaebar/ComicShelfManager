package com.example.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity //This makes a class a DB table
@Table(name = "users") //This lets us name our DB table
@Component //Makes a class a bean (stereotype annotation)
public class User {

    @Id //This makes the field the PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //This makes the PK auto-increment
    private int userId;

    //@Column isn't necessary UNLESS we need to specify constraints
    @Column(nullable = false, unique = true)
    private String username;

    //boilerplate code-------------------------

    //no args constructor
    public User() {
    }

    //all args constructor
    public User(String username, int userId) {
        this.username = username;
        this.userId = userId;
    }

    //getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //toString
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}

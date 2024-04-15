package com.example.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Set;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comic> comics;


    //boilerplate code-------------------------

    //no args constructor
    public User(Set<Comic> comics) {
        this.comics = comics;
    }

    //all args constructor
    public User(String username, int userId, Set<Comic> comics) {
        this.username = username;
        this.userId = userId;
        this.comics = comics;
    }

    public User() {

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

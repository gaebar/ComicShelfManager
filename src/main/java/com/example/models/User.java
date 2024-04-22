package com.example.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.util.Set;

@Entity // This makes a class a DB table
@Table(name = "users") // This lets us name our DB table
@Component // Commented out: Generally, entities are not managed as Spring beans unless specifically required for aspects like AOP.
public class User {

    @Id // This makes the field the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This makes the PK auto-increment
    private int userId;

    //@Column isn't necessary UNLESS we need to specify constraints
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comic> comics;

    // Boilerplate code-------------------------

    public User() { // No-args constructor
    }

    public User(String username, int userId, String password, Set<Comic> comics) {
        this.username = username;
        this.userId = userId; // Including userId in all-args constructor might lead to issues with ID conflicts
        this.password = password;
        this.comics = comics;
    }

    public User(Set<Comic> comics) { // Constructor without userId
        this.comics = comics;
    }

    // Getters and Setters
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString() method for debugging
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

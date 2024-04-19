package com.example.controllers;

import com.example.daos.UserDAO;
import com.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDAO userDAO;

    // Constructor for dependency injection
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // POST: Add a new user to the database
    @PostMapping
    public ResponseEntity<User> insertUser(@RequestBody User user) {
        User newUser = userDAO.save(user);
        if (newUser == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(201).body(newUser);
    }

    // GET: Retrieve all users from the database
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userDAO.findAll();
        return ResponseEntity.ok(users);
    }

    // GET: Retrieve a user by username
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // PUT: Update an existing user's information
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        User existingUser = userDAO.findById(id).orElse(null);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        existingUser.setUsername(updatedUser.getUsername());
        // Update other fields as necessary
        User savedUser = userDAO.save(existingUser);
        return ResponseEntity.ok(savedUser);
    }

    // DELETE: Remove a user from the database
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        User existingUser = userDAO.findById(id).orElse(null);
        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }
        userDAO.delete(existingUser);
        return ResponseEntity.ok().build();
    }
}

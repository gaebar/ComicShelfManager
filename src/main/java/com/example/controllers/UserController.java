package com.example.controllers;

import com.example.daos.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //This makes a class a bean, and converts every response to JSON for us
@RequestMapping("/users")
public class UserController {

    UserDAO userDAO;

    // Constructor injection (because this controller depends on the UserDAO)
    @Autowired
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}

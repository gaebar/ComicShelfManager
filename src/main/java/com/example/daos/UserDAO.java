package com.example.daos;

import com.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    public User findByUsername(String username);

}

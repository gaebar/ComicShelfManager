package com.example.daos;

import com.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserDAO interface extends JpaRepository for CRUD operations and custom queries
 * pertaining to 'User' entities.
 */
@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    /**
     * Custom query to find a user by their username.
     *
     * @param username the username to search for in the database
     * @return User entity if found, or null otherwise
     */
    User findByUsername(String username);
}

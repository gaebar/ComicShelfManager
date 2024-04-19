package com.example.daos;

import com.example.models.Comic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ComicDAO interface extends JpaRepository for CRUD operations and custom queries
 * pertaining to 'Comic' entities.
 */
@Repository
public interface ComicDAO extends JpaRepository<Comic, Integer> {

    /**
     * Finds all comics linked to a specific user ID.
     *
     * @param userId the ID of the user whose comics are to be retrieved
     * @return a list of Comic entities, empty if no comics found
     */
    List<Comic> findByUserId(int userId);

    /**
     * Custom JPQL query to find comics by their title or author.
     * This method allows for searching by either title, author, or both.
     *
     * @param title the title to search for (nullable)
     * @param author the author to search for (nullable)
     * @return a list of Comic entities matching the search criteria
     */
    @Query("SELECT c FROM Comic c WHERE (:title IS NULL OR c.title LIKE %:title%) AND (:author IS NULL OR c.author LIKE %:author%)")
    List<Comic> findByTitleOrAuthor(String title, String author);
}

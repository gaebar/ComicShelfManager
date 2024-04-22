package com.example.controllers;

import com.example.daos.ComicDAO;
import com.example.daos.UserDAO;
import com.example.models.Comic;
import com.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comics")
public class ComicController {

    private ComicDAO comicDAO;
    private UserDAO userDAO;

    @Autowired
    public ComicController(ComicDAO comicDAO, UserDAO userDAO) {
        this.comicDAO = comicDAO;
        this.userDAO = userDAO;
    }

    // Method to select all comics from the comics table
    @GetMapping
    public ResponseEntity<List<Comic>> getAllComics() {
        List<Comic> comics = comicDAO.findAll();
        return ResponseEntity.ok(comics);
    }


    // Method to select one comic by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Comic> getComicById(@PathVariable int id) {
        Comic comic = comicDAO.findById(id).orElse(null);
        if (comic == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comic);
    }

    // Method to select all comics that belong to a certain user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comic>> getComicsByUserId(@PathVariable int userId) {
        List<Comic> comics = comicDAO.findByUserUserId(userId);
        if (comics.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comics);
    }

    // Method to insert a new comic into the comics table
    @PostMapping("/{userId}")
    public ResponseEntity<Comic> addComic(@RequestBody Comic comic, @PathVariable int userId) {
        User u = userDAO.findById(userId).get();
        comic.setUser(u);

        Comic c = comicDAO.save(comic);

        return ResponseEntity.status(201).body(c);
    }


    // Updates an existing comic
    @PutMapping("/{comicId}")
    public ResponseEntity<Object> updateComic(@RequestBody Comic comicDetails, @PathVariable int comicId) {
        Optional<Comic> existingComic = comicDAO.findById(comicId);

        if (existingComic.isEmpty()) {
            return ResponseEntity.status(404).body("No comic found with ID: " + comicId);
        } else {
            Comic comic = existingComic.get();
            comic.setTitle(comicDetails.getTitle()); // Update title
            comic.setReleaseDate(comicDetails.getReleaseDate()); // Update release date
            comic.setAuthor(comicDetails.getAuthor()); // Update author
            Comic updatedComic = comicDAO.save(comic);
            return ResponseEntity.ok("Comic with ID " + updatedComic.getComicId() + " updated successfully.");
        }
    }


    @DeleteMapping("/{comicId}")
    public ResponseEntity<Object> deleteComic(@PathVariable int comicId){

        //first we'll get the comic by ID to see if it exists
        //error message if not!!
        Optional<Comic> g = comicDAO.findById(comicId);

        if(g.isEmpty()){
            return ResponseEntity.status(404).body("No comic found with ID of: " + comicId);
        }

        Comic comic = g.get();

        //now we can delete
        comicDAO.deleteById(comicId);

        //we could send back the entire comic, but let's send a confirmation message instead
        return ResponseEntity.accepted().body("Comic " + comic.getTitle() + " has been deleted");

    }
}


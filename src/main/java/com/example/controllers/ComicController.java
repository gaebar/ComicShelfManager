package com.example.controllers;

import com.example.daos.ComicDAO;
import com.example.models.Comic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comics")
public class ComicController {

    @Autowired
    private ComicDAO comicDAO;

    // Method to insert a new comic into the comics table
    @PostMapping
    public ResponseEntity<Comic> addComic(@RequestBody Comic comic) {
        Comic savedComic = comicDAO.save(comic);
        if (savedComic == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(201).body(savedComic);
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
        List<Comic> comics = comicDAO.findByUserId(userId);
        if (comics.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comics);
    }

    // Method to update a comic
    @PutMapping("/{id}")
    public ResponseEntity<Comic> updateComic(@PathVariable int id, @RequestBody Comic comicDetails) {
        Comic comic = comicDAO.findById(id).orElse(null);
        if (comic == null) {
            return ResponseEntity.notFound().build();
        }
        comic.setTitle(comicDetails.getTitle());
        comic.setReleaseDate(comicDetails.getReleaseDate());
        comic.setAuthor(comicDetails.getAuthor());
        comic.setUser(comicDetails.getUser());
        comicDAO.save(comic);
        return ResponseEntity.ok(comic);
    }

    // Method to delete a comic
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComic(@PathVariable int id) {
        Comic comic = comicDAO.findById(id).orElse(null);
        if (comic == null) {
            return ResponseEntity.notFound().build();
        }
        comicDAO.delete(comic);
        return ResponseEntity.ok().build();
    }

    // Optionally add another functionality of your choice here
    @GetMapping("/search")
    public ResponseEntity<List<Comic>> searchComics(@RequestParam(required = false) String title,
                                                    @RequestParam(required = false) String author) {
        List<Comic> comics = comicDAO.findByTitleOrAuthor(title, author);
        if (comics.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comics);
    }
}


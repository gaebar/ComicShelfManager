package com.example.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Entity // This tells Hibernate to make a table out of this class
@Table(name = "comics")
@Component
public class Comic {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int comicId;
    private String title;

    @Column(name = "release_date")
    private LocalDate releaseDate;
    private String author;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    public Comic() {
    }

    public Comic(String title, LocalDate releaseDate, String author) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.author = author;
    }

    public Integer getComicId() {
        return comicId;
    }

    public void setComicId(Integer comicId) {
        this.comicId = comicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Comic{" +
                "comicId=" + comicId +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", author='" + author + '\'' +
                ", user=" + user +
                '}';
    }
}

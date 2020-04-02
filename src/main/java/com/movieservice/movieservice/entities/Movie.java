package com.movieservice.movieservice.entities;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "movies")
public class Movie {
//    movieId: Long PK
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    imdbId: String Unique
    @Column(name = "imdbId")
    private String imdbId;
//    actors: String comma separated list of actors
    @Column(name = "actors")
    private String actors;
//    director: String
    @Column(name = "director")
    private String director;
//    title: String
    @Column(name = "title")
    private String title;
//    year: String (bonus: set derived from released)
    @Column(name = "year")
    private String year;
    //    year: String (bonus: set derived from released)
    @Column(name = "rating")
    private String rating;
//    released: LocalDate
    @Column(name = "released")
    private LocalDate released;
    public Movie() {

    }
    public Movie(String imdbId, String actors, String director, String title, String year, LocalDate released) {
        this.imdbId = imdbId;
        this.actors = actors;
        this.director = director;
        this.rating = "0";
        this.title = title;
        this.year = year;
        this.released = released;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(LocalDate released) {
        this.released = released;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(id, movie.id) &&
                Objects.equals(imdbId, movie.imdbId) &&
                Objects.equals(actors, movie.actors) &&
                Objects.equals(director, movie.director) &&
                Objects.equals(title, movie.title) &&
                Objects.equals(year, movie.year) &&
                Objects.equals(released, movie.released);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imdbId, actors, director, title, year, released);
    }
}

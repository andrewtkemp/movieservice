package com.movieservice.movieservice.services;

import com.movieservice.movieservice.entities.Movie;
import com.movieservice.movieservice.repositories.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
class MovieServiceTest {
    String title = "Cinderella";
    String year = "2015";
    String rated = "PG";
    LocalDate released = LocalDate.parse("2015-03-15");
    String director = "Kenneth Branagh";
    String actors = "Cate Blanchett, Lily James, Richard Madden, Helena Bonham Carter";
    String imdbID = "tt1661199";
    Movie movie;
    @Autowired
    MovieRepository repository;

    MovieService service;
    @BeforeEach
    void setup() {
        movie = new Movie(imdbID, actors, director, title, year, released);
        service = new MovieService(repository);
    }
    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }
    //    POST: add a movie to the database
    @Test
    void createMovie() {
        Movie savedMovie = service.save(movie);
        assertNotNull(savedMovie.getId());
        assertEquals(movie.getId(), savedMovie.getId());
        assertEquals(movie.getTitle(), savedMovie.getTitle());
    }
    //    GET: all movies in the database
    @Test
    void getAllMovies() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie);
        service.save(movie);
        ArrayList<Movie> savedMovies = service.findAll();
        assertEquals(movies.size(), savedMovies.size());
    }
    //    GET: movie by id
    @Test
    void getMovieById() {
        service.save(movie);
        Movie savedMovie = service.findById(movie.getId());
        assertEquals(movie.getId(), savedMovie.getImdbId());
    }
    //    GET: one movie by imdbid
    @Test
    void getByImdbId() {
        service.save(movie);
        Movie savedMovie = repository.findByimdbId(movie.getImdbId());
        assertEquals(movie.getImdbId(), savedMovie.getImdbId());
    }
    //    GET: all movies by Title
    @Test
    void getAllByTitle() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie);
        service.save(movie);
        ArrayList<Movie> savedMovies = repository.findByTitle(movie.getTitle());
        assertEquals(movies.size(), savedMovies.size());
    }
    //    GET: Search for movies by actor (optional), director (optional), genre (optional), title containing a search string (required)
    @Test
    void getAllByActor() {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(movie);
        service.save(movie);
        String actorQuery = "Cate Blanchett";
        ArrayList<Movie> savedMovies = repository.findByActor(actorQuery);
        assertEquals(movies.size(), savedMovies.size());
    }
    //    PATCH: add or update a star rating for a movie (1 - 5)
    @Test
    void updateRatingById() {
        service.save(movie);
        Movie savedMovie = service.findById(movie.getId());
        savedMovie.setRating("5");
        repository.updateMovieRating(movie.getId(), "5");

        assertEquals(movie.getRating(), savedMovie.getRating());
    }
    //    DELETE
    @Test
    void deleteById() {
        service.save(movie);
        service.delete(movie.getId());
        verify(service).delete(movie.getId());
    }
}
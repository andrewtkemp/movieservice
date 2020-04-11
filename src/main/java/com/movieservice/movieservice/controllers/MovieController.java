package com.movieservice.movieservice.controllers;

import com.movieservice.movieservice.entities.Movie;
import com.movieservice.movieservice.repositories.MovieRepository;
import com.movieservice.movieservice.services.MovieService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private MovieService service;
    private MovieRepository repository;
    public MovieController(MovieService service, MovieRepository repository){
        this.repository = repository;
        this.service = service;
    }
    //    POST: add a movie to the database
    @PostMapping
    public ResponseEntity<Movie> createOrder(@RequestBody Movie Movie){
        return ResponseEntity.ok(service.save(Movie));
    }
    //    GET: all movies in the database
    @GetMapping
    public ResponseEntity<ArrayList<Movie>> getAllMovies(){
        return ResponseEntity.ok(service.findAll());
    }
    //    GET: one movie by imdbid
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    //    GET: all movies by Title
    //    GET: Search for movies by actor (optional), director (optional), genre (optional), title containing a search string (required)
    @GetMapping("?actor={actor}")
    public ResponseEntity<ArrayList<Movie>> searchByActor(@PathVariable String actor){
        return ResponseEntity.ok(repository.findByActor(actor));
    }
    //    PATCH: add or update a star rating for a movie (1 - 5)
    @PatchMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie){
        return ResponseEntity.ok(service.updateMovieById(id, movie));
    }
    //    DELETE
    @DeleteMapping("/{id}")
    public void deleteMovieById(@PathVariable Long id){
        service.delete(id);
    }
}

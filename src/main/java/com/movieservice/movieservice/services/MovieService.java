package com.movieservice.movieservice.services;
import com.movieservice.movieservice.entities.Movie;
import com.movieservice.movieservice.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MovieService {
    private MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

        public Movie save(Movie movie) {
            return repository.save(movie);
        }

        public ArrayList<Movie> findAll() {
            return repository.findAll();
        }
        public Movie findById(Long id) {
            Movie movie = repository.findByid(id);
            return movie;
        }
        public Movie updateMovieById(Long id, Movie movie) {
            if(repository.existsById(id)){
                return repository.save(movie);
            }else {
                return null;
            }
        }

        public boolean delete(Long id) {
            if(repository.existsById(id)){
                repository.deleteById(id);
                return true;
            }
            return false;
        }

    public Movie updateMovieRating(Long id, String rating) {
        if(repository.existsById(id)){
            return repository.updateMovieRating(id, rating);
        }else {
            return null;
        }
    }

    public Movie getMovieById(Long id) {
        return repository.findByid(id);
    }

    public ArrayList<Movie> getMoviesByActor(String actorQuery) {
        String queryString = "%" + actorQuery + "%";
        return repository.findByActor(queryString);
    }

    public ArrayList<Movie> getMoviesByTitle(String title) {
        String queryString = "%" + title + "%";
        return repository.findByActor(queryString);
    }

    public Movie getMovieByImdbId(String imdbId) {
        return repository.findByimdbId(imdbId);
    }

    public ArrayList<Movie> getAllMovies() {
        return repository.findAll();
    }

    public Object findAllByTitle(String title) {
        String queryString = "%" + title + "%";
        return repository.findByActor(queryString);
    }

    public Object findByImdbId(String imdbId) {
        return repository.findByimdbId(imdbId);
    }
}

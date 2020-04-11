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

        public void delete(Long id) {
            if(repository.existsById(id)){
                repository.deleteById(id);
            }
        }
    }

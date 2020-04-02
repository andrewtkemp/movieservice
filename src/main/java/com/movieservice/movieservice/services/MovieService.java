package com.movieservice.movieservice.services;
import com.movieservice.movieservice.repositories.MovieRepository;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

}

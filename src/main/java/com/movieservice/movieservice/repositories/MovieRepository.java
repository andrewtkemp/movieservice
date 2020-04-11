package com.movieservice.movieservice.repositories;
import com.movieservice.movieservice.entities.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    ArrayList<Movie> findAll();
    Movie findByid(Long id);
}

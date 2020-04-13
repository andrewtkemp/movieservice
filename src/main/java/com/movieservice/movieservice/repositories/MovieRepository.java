package com.movieservice.movieservice.repositories;
import com.movieservice.movieservice.entities.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    ArrayList<Movie> findAll();
    Movie findByid(Long id);
    @Query(value = "UPDATE movies m SET m.rating = ?2 WHERE m.id = ?1",
            nativeQuery=true
    )
    Movie updateMovieRating(Long id, String rating);
    @Query(value = "SELECT * FROM movies m WHERE m.actors LIKE ?1", nativeQuery=true)
    ArrayList<Movie> findByActor(String actorQuery);
    @Query(value = "SELECT * FROM movies m WHERE m.imdbId = ?1", nativeQuery=true)
    Movie findByimdbId(String queryString);

    ArrayList<Movie> findByTitle(String title);
}

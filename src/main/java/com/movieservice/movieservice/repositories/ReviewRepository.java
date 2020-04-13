package com.movieservice.movieservice.repositories;

import com.movieservice.movieservice.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.bind.JAXBPermission;

public interface ReviewRepository extends JpaRepository <Review, Long>{
    Review findReviewByImdbId(String imdbId);
    //Movie findTwoMovieReviewsByImdbId(String imdbId);
    Review findAllMoviesByTitle(String title);
    boolean deleteById(long movieId);
}
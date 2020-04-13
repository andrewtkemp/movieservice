package com.movieservice.movieservice.controllers;

import com.movieservice.movieservice.entities.Review;
import com.movieservice.movieservice.services.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    ReviewService reviewService;

    public ReviewController(ReviewService reviewService){
        this.reviewService = reviewService;
    }

    @PostMapping
    public Review createMovie(@RequestBody Review input){
        return reviewService.postReview(input);
    }

    @GetMapping
    public List<Review> getAllReviews(){
        return reviewService.getAllReviews();
    }

    @GetMapping("/imdbId/{imdbId}")
    public Review getOneMovieReviewByimdbId(@PathVariable String imdbId){
        return reviewService.findReviewByImdbId(imdbId);
    }

    @PutMapping("/rating/{reviewId}")
    public Review updateMovieReviewWithStarRating(@PathVariable long reviewId, @RequestBody Review review){
        return reviewService.updateMovieWithStarRating(reviewId, review);
    }

    @DeleteMapping("/{id}")
    public boolean updateMovie(@PathVariable long id){
        return reviewService.deleteById(id);
    }
}
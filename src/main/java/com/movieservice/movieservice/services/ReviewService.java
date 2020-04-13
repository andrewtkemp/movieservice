package com.movieservice.movieservice.services;

import com.movieservice.movieservice.entities.Review;
import com.movieservice.movieservice.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReviewService {

    ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public Review postReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    public Review findReviewByImdbId(String imdbId) {
        return reviewRepository.findReviewByImdbId(imdbId);
    }

    public Review updateMovieWithStarRating(long reviewId, Review rating) {
        Review newReview = updateMovieWithStarRating(reviewId, rating);
        newReview.update(newReview);
        return postReview(newReview);
    }

    public boolean deleteById (long reviewId){
        return reviewRepository.deleteById(reviewId);
    }
}

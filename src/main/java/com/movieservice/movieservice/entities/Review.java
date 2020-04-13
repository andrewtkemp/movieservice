package com.movieservice.movieservice.entities;


import javax.persistence.*;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId")
    private long reviewId;
    @Column(name = "email")
    private String email;
    @Column(name = "imdbId")
    private String imdbId;
    @Column(name = "title")
    private String title;
    @Column(name = "rating")
    private long rating;
    @Column(name = "text")
    private String text;

    public Review() {
    }

    public Review(long reviewId, String email, String imdbId, String title, long rating, String text){
        this.reviewId = reviewId;
        this.email = email;
        this.imdbId = imdbId;
        this.title = title;
        this.rating = rating;
        this.text = text;
    }

    public Review(String email, String imdbId, String title, long rating, String text){
        this.reviewId = reviewId;
        this.email = email;
        this.imdbId = imdbId;
        this.title = title;
        this.rating = rating;
        this.text = text;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return reviewId == review.reviewId &&
                rating == review.rating &&
                Objects.equals(email, review.email) &&
                Objects.equals(imdbId, review.imdbId) &&
                Objects.equals(title, review.title) &&
                Objects.equals(text, review.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, email, imdbId, title, rating, text);
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", email='" + email + '\'' +
                ", imdbId='" + imdbId + '\'' +
                ", title='" + title + '\'' +
                ", rating=" + rating +
                ", text='" + text + '\'' +
                '}';
    }

    public void update(Review updateReview){
        if(updateReview.getReviewId()!=0)this.setReviewId(updateReview.getReviewId());
        if(updateReview.getEmail()!=null)this.setEmail(updateReview.getEmail());
        if(updateReview.getTitle()!=null)this.setTitle(updateReview.getTitle());
        if(updateReview.getRating()!=0)this.setRating(updateReview.getRating());
        if(updateReview.getText()!=null)this.setTitle(updateReview.getText());
        if(updateReview.getImdbId()!=null)this.setImdbId(updateReview.getImdbId());
    }
}
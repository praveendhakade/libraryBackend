package com.library.services;

import com.library.models.Review;
import com.library.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private  ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public List<Review> getReviewsByBookId(Long bookId) {
        return reviewRepository.findAllByBookId(bookId);
    }

    public Review addReview(Review review) {
        Review savedReview = new Review();
        savedReview.setBookId(review.getBookId());
        savedReview.setReviewDescription(review.getReviewDescription());
        savedReview.setUserEmail(review.getUserEmail());
        savedReview.setDate(new Date());
        savedReview.setRating(review.getRating());

        return reviewRepository.save(savedReview);
    }
}

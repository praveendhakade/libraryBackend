package com.library.contollers;

import com.library.models.ApiResponse;
import com.library.models.Review;
import com.library.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("reviews")
    public List<Review> getReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("reviews/{bookId}")
    public List<Review> getReviewsByBookId(@PathVariable Long bookId) {
        return reviewService.getReviewsByBookId(bookId);
    }

    @PostMapping("add/reviews")
    public ApiResponse<Review> addReview(@RequestBody Review review) {
        Review reviewSaved = reviewService.addReview(review);
        ApiResponse<Review> apiResponse = new ApiResponse<>();

        if (reviewSaved != null) {
            apiResponse.setData(reviewSaved);
            apiResponse.setSuccess(true);
            apiResponse.setMessage("Added review successfully");
        } else {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("Failed to add review");
            apiResponse.setData(null);
        }

        return apiResponse;
    }
}

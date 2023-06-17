package me.dongwook.review.api;

import lombok.RequiredArgsConstructor;
import me.dongwook.review.api.request.CreateReviewRequest;
import me.dongwook.review.service.ReviewService;
import me.dongwook.review.service.dto.ReviewDto;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewApi {

    private final ReviewService reviewService;

    @PostMapping("/review")
    public void createReview(
            @RequestBody CreateReviewRequest request
    ) {
        reviewService.createReview(request.getRestaurantId(), request.getContent(),
                request.getScore());
    }

    @DeleteMapping("/review/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
    }

    @GetMapping("/restaurant/{restaurantId}/reviews")
    public ReviewDto getRestaurantReviews(@PathVariable Long restaurantId,
            @RequestParam Integer offset,
            @RequestParam Integer limit
    ) {
        return reviewService.getRestaurantReview(restaurantId,
                PageRequest.of(offset / limit, limit));

    }
}

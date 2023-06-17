package me.dongwook.review.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import me.dongwook.review.model.ReviewEntity;
import me.dongwook.review.repository.RestaurantRepository;
import me.dongwook.review.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final RestaurantRepository restaurantRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public void createReview(Long restaurantId, String content, Double score) {
        restaurantRepository.findById(restaurantId).orElseThrow();

        ReviewEntity review = ReviewEntity.builder()
                .restaurantId(restaurantId)
                .content(content)
                .score(score)
                .createdAt(LocalDateTime.now())
                .build();

        reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        ReviewEntity review = reviewRepository.findById(reviewId).orElseThrow();
        reviewRepository.delete(review);
    }

}

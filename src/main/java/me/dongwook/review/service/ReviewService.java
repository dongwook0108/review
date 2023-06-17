package me.dongwook.review.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import me.dongwook.review.model.ReviewEntity;
import me.dongwook.review.repository.RestaurantRepository;
import me.dongwook.review.repository.ReviewRepository;
import me.dongwook.review.service.dto.ReviewDto;
import me.dongwook.review.service.dto.ReviewDto.ReviewDtoPage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
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

    public ReviewDto getRestaurantReview(Long restaurantId, Pageable page) {

        Double avgScore = reviewRepository.getAvgScoreByRestaurantId(restaurantId);
        Slice<ReviewEntity> reviews = reviewRepository.findSliceByRestaurantId(
                restaurantId, page);

        return ReviewDto.builder()
                .avgScore(avgScore)
                .reviews(reviews.getContent())
                .page(
                        ReviewDtoPage.builder()
                                .offset(page.getPageNumber() * page.getPageSize())
                                .limit(page.getPageSize())
                                .build()
                )
                .build();
    }


}

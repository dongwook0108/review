package me.dongwook.review.service.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import me.dongwook.review.model.ReviewEntity;

@AllArgsConstructor
@Getter
@Builder
public class ReviewDto {

    private Double avgScore;
    private List<ReviewEntity> reviews = new ArrayList<>();
    private ReviewDtoPage page;

    @Builder
    @AllArgsConstructor
    @Getter
    public static class ReviewDtoPage {
        private Integer offset;
        private Integer limit;
    }


}

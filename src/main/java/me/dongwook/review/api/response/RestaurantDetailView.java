package me.dongwook.review.api.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class RestaurantDetailView {

    private Long id;
    private String name;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Menu> menus = new ArrayList<>();


    @AllArgsConstructor
    @Getter
    @Builder
    public static class Menu {

        private Long id;
        private String name;
        private Integer price;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

}

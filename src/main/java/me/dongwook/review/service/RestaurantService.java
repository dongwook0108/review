package me.dongwook.review.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import me.dongwook.review.api.request.CreateAndEditRestaurantRequest;
import me.dongwook.review.api.response.RestaurantDetailView;
import me.dongwook.review.api.response.RestaurantView;
import me.dongwook.review.model.MenuEntity;
import me.dongwook.review.model.RestaurantEntity;
import me.dongwook.review.repository.MenuRepository;
import me.dongwook.review.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public RestaurantEntity createRestaurant(CreateAndEditRestaurantRequest request) {

        RestaurantEntity restaurant = RestaurantEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        restaurantRepository.save(restaurant);

        request.getMenus().forEach((menu) -> {
            MenuEntity menuEntity = MenuEntity.builder()
                    .restaurantId(restaurant.getId())
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            menuRepository.save(menuEntity);
        });

        return restaurant;
    }

    @Transactional
    public void editRestaurant(
            Long restaurantId,
            CreateAndEditRestaurantRequest request) {

        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("없는 식당입니다."));

        restaurant.changeNameAndAddress(request.getName(), restaurant.getAddress());
        restaurantRepository.save(restaurant);

        List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurantId);
        menuRepository.deleteAll(menus);

        request.getMenus().forEach((menu) -> {
            MenuEntity menuEntity = MenuEntity.builder()
                    .restaurantId(restaurantId)
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();

            menuRepository.save(menuEntity);
        });
    }

    @Transactional
    public void deleteRestaurant(Long restaurantId) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("찾는 식당이 없슴둥"));
        restaurantRepository.delete(restaurant);

        List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurantId);
        menuRepository.deleteAll(menus);
    }

    @Transactional(readOnly = true)
    public List<RestaurantView> getAllRestaurants() {
        List<RestaurantEntity> restaurants = restaurantRepository.findAll();

        return restaurants.stream().map((restaurant) -> RestaurantView.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .createdAt(restaurant.getCreatedAt())
                .updatedAt(restaurant.getUpdatedAt())
                .build()
        ).toList();
    }

    @Transactional(readOnly = true)
    public RestaurantDetailView getRestaurantDetail(Long restaurantId) {

        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow();

        List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurantId);

        return RestaurantDetailView.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .updatedAt(restaurant.getUpdatedAt())
                .createdAt(restaurant.getCreatedAt())
                .menus(
                        menus.stream().map((menu) -> RestaurantDetailView.Menu.builder()
                                .id(menu.getId())
                                .name(menu.getName())
                                .price(menu.getPrice())
                                .createdAt(menu.getCreatedAt())
                                .updatedAt(menu.getUpdatedAt())
                                .build()
                        ).toList()
                )
                .build();



    }


}

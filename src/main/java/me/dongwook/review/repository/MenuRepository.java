package me.dongwook.review.repository;

import java.util.List;
import me.dongwook.review.model.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {

    public List<MenuEntity> findAllByRestaurantId(Long restaurantId);

}

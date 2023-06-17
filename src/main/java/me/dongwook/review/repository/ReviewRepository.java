package me.dongwook.review.repository;

import me.dongwook.review.model.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long>, ReviewRepositoryCustom {

}

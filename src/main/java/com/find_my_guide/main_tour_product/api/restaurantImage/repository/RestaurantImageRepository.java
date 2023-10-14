package com.find_my_guide.main_tour_product.api.restaurantImage.repository;

import com.find_my_guide.main_tour_product.api.restaurantImage.domain.RestaurantImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantImageRepository extends JpaRepository<RestaurantImage, Long> {
}

package com.find_my_guide.main_tour_product.api.restaurant.repository;

import com.find_my_guide.main_tour_product.api.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}

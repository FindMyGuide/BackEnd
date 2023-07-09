package com.find_my_guide.api.restaurant.repository;

import com.find_my_guide.api.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}

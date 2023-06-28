package com.findMyGuide.api.restaurant.repository;

import com.findMyGuide.api.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}

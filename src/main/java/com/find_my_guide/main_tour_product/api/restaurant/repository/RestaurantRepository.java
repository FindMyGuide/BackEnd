package com.find_my_guide.main_tour_product.api.restaurant.repository;

import com.find_my_guide.main_tour_product.api.restaurant.domain.Restaurant;
import com.find_my_guide.main_tour_product.api.tourLocation.domain.TourLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query(value = "SELECT * " +
            "FROM restaurant " +
            "order by RAND() limit 50", nativeQuery = true)
    List<Restaurant> findAllRand();
}

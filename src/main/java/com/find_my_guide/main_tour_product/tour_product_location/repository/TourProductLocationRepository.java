package com.find_my_guide.main_tour_product.tour_product_location.repository;

import com.find_my_guide.main_tour_product.tour_product_location.domain.TourProductLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourProductLocationRepository extends JpaRepository<TourProductLocation, Long> {

}

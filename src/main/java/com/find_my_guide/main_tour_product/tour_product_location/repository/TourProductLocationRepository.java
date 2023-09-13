package com.find_my_guide.main_tour_product.tour_product_location.repository;

import com.find_my_guide.main_tour_product.tour_product_location.domain.TourProductLocation;
import com.find_my_guide.main_tour_product.tour_product_theme.domain.TourProductTheme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourProductLocationRepository extends JpaRepository<TourProductLocation, Long> {
        List<TourProductLocation> findByTourProduct_TourProductId(Long tourProductId);
}

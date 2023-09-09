package com.find_my_guide.main_tour_product.tour_product_theme.repository;

import com.find_my_guide.main_tour_product.tour_product_theme.domain.TourProductTheme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourProductThemeRepository extends JpaRepository<TourProductTheme,Long> {
    List<TourProductTheme> findByTourProduct_TourProductId(Long tourProductId);
}

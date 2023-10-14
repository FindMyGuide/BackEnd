package com.find_my_guide.main_tour_product.tour_product_location.repository;

import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product_location.domain.TourProductLocation;
import com.find_my_guide.main_tour_product.tour_product_theme.domain.TourProductTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TourProductLocationRepository extends JpaRepository<TourProductLocation, Long> {
        List<TourProductLocation> findByTourProduct_TourProductId(Long tourProductId);

        @Query("SELECT tpl.tourProduct FROM TourProductLocation tpl WHERE tpl.location.title = :title")
        List<TourProduct> findTourProductsByLocationTitle(@Param("title") Title title);

        @Query("SELECT tpl.tourProduct FROM TourProductLocation tpl WHERE tpl.location.title.title LIKE CONCAT('%', :titleValue, '%')")
        List<TourProduct> findTourProductsByContainingLocationTitle(@Param("titleValue") String titleValue);


}





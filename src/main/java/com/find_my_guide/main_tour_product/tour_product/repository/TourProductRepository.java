package com.find_my_guide.main_tour_product.tour_product.repository;

import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TourProductRepository extends JpaRepository<TourProduct, Long> {

    @Query("SELECT tp FROM TourProduct tp " +
            "JOIN tp.tourProductThemes tpt " +
            "JOIN tpt.theme t " +
            "WHERE t.title.title LIKE :titleValue")
    List<TourProduct> findByThemeTitleContaining(@Param("titleValue") String titleValue);

    List<TourProduct> findByLanguagesIn(List<Languages> languages);

    @Query("SELECT tp FROM TourProduct tp WHERE tp.title.title LIKE :titleValue")
    List<TourProduct> findByTitleContaining(@Param("titleValue") String titleValue);





}

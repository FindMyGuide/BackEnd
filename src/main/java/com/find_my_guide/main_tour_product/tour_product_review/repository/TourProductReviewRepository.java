package com.find_my_guide.main_tour_product.tour_product_review.repository;

import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product_review.domain.TourProductReview;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourProductReviewRepository extends JpaRepository<TourProductReview,Long> {

    List<TourProductReview> findByTourProductOrderByCreatedAtDesc(TourProduct tourProduct, Pageable pageable);

}

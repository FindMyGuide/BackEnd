package com.find_my_guide.tour_product_review.repository;

import com.find_my_guide.tour_product_review.domain.TourProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourProductReviewRepository extends JpaRepository<TourProductReview,Long> {
}

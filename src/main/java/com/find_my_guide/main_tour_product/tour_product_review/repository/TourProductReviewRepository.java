package com.find_my_guide.main_tour_product.tour_product_review.repository;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product_review.domain.TourProductReview;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourProductReviewRepository extends JpaRepository<TourProductReview,Long> {

    List<TourProductReview> findByTourProductOrderByCreatedAtDesc(TourProduct tourProduct, Pageable pageable);

    @Query("SELECT r FROM TourProductReview r JOIN r.tourProduct p JOIN p.tourHistoryManagers h WHERE h.guide.idx = :guideId")
    List<TourProductReview> findAllByGuideId(@Param("guideId") Long guideId);

    List<TourProductReview> findAllByMember(Member member);
}

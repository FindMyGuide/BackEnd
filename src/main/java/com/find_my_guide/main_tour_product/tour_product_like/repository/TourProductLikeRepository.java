package com.find_my_guide.main_tour_product.tour_product_like.repository;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product_like.domain.TourProductLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TourProductLikeRepository extends JpaRepository<TourProductLike,Long> {

    long countByTourProduct_TourProductId(Long tourProductId);

    Optional<TourProductLike> findByMemberAndTourProduct(Member member, TourProduct tourProduct);

    List<TourProductLike> findByMember(Member member);


}

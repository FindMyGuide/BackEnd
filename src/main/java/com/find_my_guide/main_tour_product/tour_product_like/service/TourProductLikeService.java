package com.find_my_guide.main_tour_product.tour_product_like.service;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.repository.TourProductRepository;
import com.find_my_guide.main_tour_product.tour_product_like.domain.TourProductLike;
import com.find_my_guide.main_tour_product.tour_product_like.dto.TourProductLikeRequest;
import com.find_my_guide.main_tour_product.tour_product_like.dto.TourProductLikeResponse;
import com.find_my_guide.main_tour_product.tour_product_like.repository.TourProductLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TourProductLikeService {

    private final TourProductLikeRepository tourProductLikeRepository;
    private final TourProductRepository tourProductRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public TourProductLikeResponse addLike(TourProductLikeRequest request) {
        TourProduct tourProduct = tourProductRepository.findById(request.getTourProductId())
                .orElseThrow(() -> new IllegalArgumentException("TourProduct not found"));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        TourProductLike tourProductLike = TourProductLike.builder()
                .tourProduct(tourProduct)
                .member(member)
                .build();

        tourProductLike = tourProductLikeRepository.save(tourProductLike);

        return new TourProductLikeResponse(tourProduct.getTourProductId(), member.getIdx());
    }
}

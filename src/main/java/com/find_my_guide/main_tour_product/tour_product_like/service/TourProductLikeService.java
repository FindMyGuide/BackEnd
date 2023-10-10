package com.find_my_guide.main_tour_product.tour_product_like.service;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductResponse;
import com.find_my_guide.main_tour_product.tour_product.repository.TourProductRepository;
import com.find_my_guide.main_tour_product.tour_product_like.domain.TourProductLike;
import com.find_my_guide.main_tour_product.tour_product_like.dto.TourProductLikeRequest;
import com.find_my_guide.main_tour_product.tour_product_like.dto.TourProductLikeResponse;
import com.find_my_guide.main_tour_product.tour_product_like.repository.TourProductLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourProductLikeService {

    private final TourProductLikeRepository tourProductLikeRepository;
    private final TourProductRepository tourProductRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public TourProductLikeResponse addLike(TourProductLikeRequest request) {
        Member member = getMember(request);
        TourProduct tourProduct = getTourProduct(request);

        if(tourProductLikeRepository.findByMemberAndTourProduct(member, tourProduct).isPresent()) {
            throw new IllegalArgumentException("이미 좋아요 눌렀어요");
        }

        TourProductLike tourProductLike = TourProductLike.builder()
                .tourProduct(tourProduct)
                .member(member)
                .build();

        tourProductLikeRepository.save(tourProductLike);

        return new TourProductLikeResponse(tourProduct.getTourProductId(), member.getIdx());
    }

    @Transactional
    public void removeLike(TourProductLikeRequest request) {
        Member member = getMember(request);
        TourProduct tourProduct = getTourProduct(request);

        TourProductLike existingLike = tourProductLikeRepository.findByMemberAndTourProduct(member, tourProduct)
                .orElseThrow(() -> new IllegalArgumentException("좋아요 안눌렀어요"));

        tourProductLikeRepository.delete(existingLike);
    }

    public List<TourProductResponse> findAllLikeTourProduct(String email){
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        List<TourProductLike> likedTourProducts = tourProductLikeRepository.findByMember(member);

        return likedTourProducts.stream()
                .map(like -> new TourProductResponse(like.getTourProduct()))
                .collect(Collectors.toList());
    }

    private TourProduct getTourProduct(TourProductLikeRequest request) {
        return tourProductRepository.findById(request.getTourProductId())
                .orElseThrow(() -> new IllegalArgumentException("TourProduct not found"));
    }

    private Member getMember(TourProductLikeRequest request) {
        return memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
    }
}

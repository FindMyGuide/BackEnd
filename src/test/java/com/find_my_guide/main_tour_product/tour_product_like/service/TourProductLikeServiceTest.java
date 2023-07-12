package com.find_my_guide.main_tour_product.tour_product_like.service;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.service.TourProductService;
import com.find_my_guide.main_tour_product.tour_product_like.domain.TourProductLike;
import com.find_my_guide.main_tour_product.tour_product_like.dto.TourProductLikeRequest;
import com.find_my_guide.main_tour_product.tour_product.repository.TourProductRepository;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import com.find_my_guide.main_tour_product.tour_product_like.repository.TourProductLikeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TourProductLikeServiceTest {

    @Mock
    private TourProductLikeRepository tourProductLikeRepository;
    @Mock
    private TourProductRepository tourProductRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private TourProductService tourProductService;

    private TourProductLikeService tourProductLikeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tourProductLikeService = new TourProductLikeService(tourProductLikeRepository, tourProductRepository, memberRepository);
    }

    @Test
    void addLike() {
        Long memberId = 1L;
        Long tourProductId = 1L;
        TourProductLikeRequest tourProductLikeRequest = new TourProductLikeRequest(memberId, tourProductId);

        TourProduct tourProduct = TourProduct.builder().tourProductId(tourProductId).build();
        Member member = Member.builder().idx(memberId).build();

        when(tourProductRepository.findById(tourProductId)).thenReturn(Optional.of(tourProduct));
        when(memberRepository.findById(memberId)).thenReturn(Optional.of(member));

        tourProductLikeService.addLike(tourProductLikeRequest);
    }



}

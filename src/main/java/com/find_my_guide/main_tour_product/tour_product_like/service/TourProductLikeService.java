package com.find_my_guide.main_tour_product.tour_product_like.service;

import com.find_my_guide.main_tour_product.tour_product_like.repository.TourProductLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TourProductLikeService {

    private final TourProductLikeRepository tourProductLikeRepository;

}

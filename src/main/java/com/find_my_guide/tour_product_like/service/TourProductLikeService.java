package com.find_my_guide.tour_product_like.service;

import com.find_my_guide.tour_product_like.repository.TourProductLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TourProductLikeService {

    private final TourProductLikeRepository tourProductLikeRepository;

}

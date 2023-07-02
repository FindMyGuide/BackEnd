package com.find_my_guide.tour_product.service;

import com.find_my_guide.common.validation_field.Content;
import com.find_my_guide.common.validation_field.Title;
import com.find_my_guide.tour_product.domain.TourProduct;
import com.find_my_guide.tour_product.dto.TourProductRequest;
import com.find_my_guide.tour_product.dto.TourProductResponse;
import com.find_my_guide.tour_product.repository.TourProductRepository;
import com.find_my_guide.tour_product_review.domain.TourProductReview;
import com.find_my_guide.tour_product_review.dto.TourProductReviewResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TourProductService {

    private final TourProductRepository tourProductRepository;

    @Transactional
    public TourProductResponse register(TourProductRequest tourProductRequest) {
        return new TourProductResponse(tourProductRepository.save(tourProductRequest.toTourProduct()));

    }

    @Transactional
    public TourProductResponse update(Long id, TourProductRequest tourProductRequest) {
        TourProduct tourProduct = findById(id);
        tourProduct.update(new Title(tourProductRequest.getTitle()), new Content(tourProductRequest.getContent()));
        return new TourProductResponse(tourProduct);

    }

    @Transactional
    public TourProductResponse delete(Long id) {
        TourProduct tourProduct = findById(id);
        tourProductRepository.delete(tourProduct);
        return new TourProductResponse(tourProduct);

    }




    public TourProductResponse detail(Long id) {
        return new TourProductResponse(findById(id));
    }


    private TourProduct findById(Long id) {
        return tourProductRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 관광 상품 입니다."));
    }


}

package com.find_my_guide.main_tour_product.tour_product.service;

import com.find_my_guide.main_tour_product.available_reservation_date.dto.AvailableDateRequest;
import com.find_my_guide.main_tour_product.available_reservation_date.dto.AvailableDateResponse;
import com.find_my_guide.main_tour_product.available_reservation_date.service.AvailableService;
import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductRequest;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductResponse;
import com.find_my_guide.main_tour_product.tour_product.repository.TourProductRepository;
import com.find_my_guide.main_tour_product.tour_product_theme.dto.TourProductThemeRequest;
import com.find_my_guide.main_tour_product.tour_product_theme.service.TourProductThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TourProductService {

    private final TourProductRepository tourProductRepository;
    private final TourProductThemeService tourProductThemeService;
    private final AvailableService availableService;

    @Transactional
    public TourProductResponse addTourProduct(TourProductRequest tourProductRequest) {
        TourProduct tourProduct = tourProductRequest.toTourProduct();
        tourProduct = tourProductRepository.save(tourProduct);

        // 테마 등록
        for(Long themeId : tourProductRequest.getThemeIds()) {
            TourProductThemeRequest tourProductThemeRequest = new TourProductThemeRequest(themeId, tourProduct.getTourProductId());
            tourProductThemeService.addThemeToTourProduct(tourProductThemeRequest);
        }

        // 날짜 등록
        for(LocalDate availableDate : tourProductRequest.getAvailableDates()) {
            AvailableDateRequest availableDateRequest = new AvailableDateRequest(availableDate);
            availableService.registerDate(tourProduct.getTourProductId(), availableDateRequest);
        }

        return new TourProductResponse(tourProduct);
    }
    @Transactional
    public TourProductResponse update(Long id, TourProductRequest tourProductRequest) {
        TourProduct tourProduct = findById(id);
        tourProduct.update(new Title(tourProductRequest.getTitle()), new Content(tourProductRequest.getContent()));
        return new TourProductResponse(tourProductRepository.save(tourProduct));

    }

    @Transactional
    public TourProductResponse delete(Long postId) {
        TourProduct tourProduct = findById(postId);
        tourProductRepository.delete(tourProduct);
        return new TourProductResponse(tourProduct);

    }

    public List<TourProductResponse> showTourProductList() {
        return tourProductRepository.findAll().stream()
                .map(TourProductResponse::new)
                .collect(Collectors.toList());
    }

    public List<AvailableDateResponse> availableDates(Long postId) {
        TourProduct tourProduct = findById(postId);

        return tourProduct.getAvailableDates().stream()
                .map(AvailableDateResponse::new)
                .collect(Collectors.toList());
    }


    public TourProductResponse detail(Long id) {
        return new TourProductResponse(findById(id));
    }


    private TourProduct findById(Long id) {
        return tourProductRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 관광 상품 입니다."));
    }


}

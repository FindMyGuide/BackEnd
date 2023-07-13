package com.find_my_guide.main_tour_product.tour_product_theme.service;

import com.find_my_guide.main_tour_product.theme.domain.Theme;
import com.find_my_guide.main_tour_product.theme.repository.ThemeRepository;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.repository.TourProductRepository;
import com.find_my_guide.main_tour_product.tour_product_theme.domain.TourProductTheme;
import com.find_my_guide.main_tour_product.tour_product_theme.dto.TourProductThemeRequest;
import com.find_my_guide.main_tour_product.tour_product_theme.dto.TourProductThemeResponse;
import com.find_my_guide.main_tour_product.tour_product_theme.repository.TourProductThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TourProductThemeService {

    private final TourProductRepository tourProductRepository;

    private final ThemeRepository themeRepository;

    private final TourProductThemeRepository tourProductThemeRepository;


    @Transactional
    public TourProductThemeResponse addThemeToTourProduct(TourProductThemeRequest tourProductThemeRequest) {

        TourProductTheme tourProductTheme = TourProductTheme.builder().
                tourProduct(findTourProduct(tourProductThemeRequest)).
                theme(findTheme(tourProductThemeRequest)).build();

        return new TourProductThemeResponse(tourProductThemeRepository.save(tourProductTheme));
    }

    private Theme findTheme(TourProductThemeRequest tourProductThemeRequest) {
        return themeRepository.findById(tourProductThemeRequest.getThemeId()).orElseThrow(() -> new IllegalArgumentException("이 테마는 없습니다"));
    }

    private TourProduct findTourProduct(TourProductThemeRequest tourProductThemeRequest) {
        return tourProductRepository.findById(tourProductThemeRequest.getTourProductId()).orElseThrow(() -> new IllegalArgumentException("이 투어는 존재하지 않습니다."));
    }

}

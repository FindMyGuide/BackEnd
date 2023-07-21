package com.find_my_guide.main_tour_product.want_tour_product_theme.service;


import com.find_my_guide.main_tour_product.theme.domain.Theme;
import com.find_my_guide.main_tour_product.theme.repository.ThemeRepository;
import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import com.find_my_guide.main_tour_product.want_tour_product.dto.WantTourProductRequest;
import com.find_my_guide.main_tour_product.want_tour_product.repository.WantTourProductRepository;
import com.find_my_guide.main_tour_product.want_tour_product_theme.domain.WantTourProductTheme;
import com.find_my_guide.main_tour_product.want_tour_product_theme.dto.WantTourProductThemeRequest;
import com.find_my_guide.main_tour_product.want_tour_product_theme.dto.WantTourProductThemeResponse;
import com.find_my_guide.main_tour_product.want_tour_product_theme.repository.WantTourProductThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WantTourProductThemeService {

    private final WantTourProductRepository wantTourProductRepository;

    private final ThemeRepository themeRepository;

    private final WantTourProductThemeRepository wantTourProductThemeRepository;


    @Transactional
    public WantTourProductThemeResponse addThemeToWantTourProduct(WantTourProductThemeRequest wantTourProductThemeRequest) {
        return new WantTourProductThemeResponse(
                wantTourProductThemeRepository.save(
                        WantTourProductTheme.builder()
                .wantTourProduct(findWantTourProduct(wantTourProductThemeRequest))
                .theme(findTheme(wantTourProductThemeRequest))
                .build()));
    }

    private Theme findTheme(WantTourProductThemeRequest wantTourProductThemeRequest) {
        return themeRepository.findById(wantTourProductThemeRequest.getThemeId())
                .orElseThrow(() -> new IllegalArgumentException("theme not found"));
    }

    private WantTourProduct findWantTourProduct(WantTourProductThemeRequest wantTourProductThemeRequest) {
        return wantTourProductRepository.findById(wantTourProductThemeRequest.getWantTourProductId())
                .orElseThrow(() -> new IllegalArgumentException(" not found "));
    }
}

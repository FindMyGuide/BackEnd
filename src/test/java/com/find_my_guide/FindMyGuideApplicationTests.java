package com.find_my_guide;

import com.find_my_guide.theme.domain.Theme;
import com.find_my_guide.theme.domain.ThemeTitle;
import com.find_my_guide.theme.repository.ThemeRepository;
import com.find_my_guide.tour_product.domain.TourProduct;
import com.find_my_guide.tour_product.repository.TourProductRepository;
import com.find_my_guide.tour_product_theme.domain.TourProductTheme;
import com.find_my_guide.tour_product_theme.repository.TourProductThemeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
class FindMyGuideApplicationTests {

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private TourProductThemeRepository tourProductThemeRepository;


    @Autowired
    private TourProductRepository tourProductRepository;

    @Test
    @Transactional
    @DisplayName("테마 저장")
    void saveTheme() {
        Theme theme = Theme.builder().
                themeTitle(new ThemeTitle("식도락")).
                build();

        Theme save = themeRepository.save(theme);

        TourProduct tourProduct = TourProduct.builder()
                .title("범규의 관광")
                .content("범광")
                .createdAt(LocalDateTime.now())
                .updatedAt(null)
                .build();

        TourProduct save1 = tourProductRepository.save(tourProduct);

        TourProductTheme build = TourProductTheme.builder()
                .tourProduct(save1)
                .theme(save)
                .build();

        TourProductTheme save2 = tourProductThemeRepository.save(build);


        Assertions.assertThat(save2.getTheme().getThemeTitle().getTitle())
                .isEqualTo("식도락");



    }

}

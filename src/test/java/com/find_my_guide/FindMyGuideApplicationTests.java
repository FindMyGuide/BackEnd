package com.find_my_guide;

import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.theme.domain.Theme;
import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.theme.repository.ThemeRepository;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.repository.TourProductRepository;
import com.find_my_guide.main_tour_product.tour_product_theme.domain.TourProductTheme;
import com.find_my_guide.main_tour_product.tour_product_theme.repository.TourProductThemeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class FindMyGuideApplicationTests {

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private TourProductThemeRepository tourProductThemeRepository;


    @Autowired
    private TourProductRepository tourProductRepository;

//    @Test
//    @Transactional
//    @DisplayName("테마 저장")
//    void saveTheme() {
//        Theme theme = Theme.builder().
//                title(new Title("식도락")).
//                build();
//
//        Theme save1 = themeRepository.save(theme);
//
//        TourProduct tourProduct = TourProduct.builder()
//                .title(new Title("범광"))
//                .content(new Content("범광컨텐츠"))
//                .build();
//
//        TourProduct save2 = tourProductRepository.save(tourProduct);
//
//        TourProductTheme build = TourProductTheme.builder()
//                .tourProduct(save2)
//                .theme(save1)
//                .build();
//
//        TourProductTheme save3 = tourProductThemeRepository.save(build);
//
//
//        Assertions.assertThat(save3.getTheme().getTitle().getTitle())
//                .isEqualTo("식도락");
//
//    }

    @Test
    void saveTourProduct(){



    }

}

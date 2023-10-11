package com.find_my_guide.main_tour_product.want_tour_product_theme.repository;

import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import com.find_my_guide.main_tour_product.want_tour_product_theme.domain.WantTourProductTheme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WantTourProductThemeRepository extends JpaRepository<WantTourProductTheme,Long> {
    List<WantTourProductTheme> findByWantTourProduct(WantTourProduct wantTourProduct);

}

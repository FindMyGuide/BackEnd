package com.find_my_guide.main_tour_product.theme.repository;

import com.find_my_guide.main_tour_product.theme.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme,Long> {
}

package com.find_my_guide.main_tour_product.want_tour_product.repository;

import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WantTourProductRepository extends JpaRepository<WantTourProduct, Long> {
}

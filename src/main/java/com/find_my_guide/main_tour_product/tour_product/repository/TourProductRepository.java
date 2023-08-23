package com.find_my_guide.main_tour_product.tour_product.repository;

import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourProductRepository extends JpaRepository<TourProduct, Long> {


}

package com.find_my_guide.main_tour_product.self_tour_location.repository;

import com.find_my_guide.main_tour_product.self_tour_location.domain.SelfTourLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelfTourLocationRepository extends JpaRepository<SelfTourLocation, Long> {
}

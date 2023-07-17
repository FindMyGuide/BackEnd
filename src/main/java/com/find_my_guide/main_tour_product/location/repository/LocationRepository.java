package com.find_my_guide.main_tour_product.location.repository;

import com.find_my_guide.main_tour_product.location.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}

package com.find_my_guide.main_tour_product.api.festival.repository;

import com.find_my_guide.main_tour_product.api.festival.domain.Festival;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FestivalRepository extends JpaRepository<Festival, Long> {
}

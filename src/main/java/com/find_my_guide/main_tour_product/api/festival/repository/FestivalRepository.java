package com.find_my_guide.main_tour_product.api.festival.repository;

import com.find_my_guide.main_tour_product.api.festival.domain.Festival;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FestivalRepository extends JpaRepository<Festival, Long> {

    Optional<Festival> findById(Long id);
}

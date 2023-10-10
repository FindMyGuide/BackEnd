package com.find_my_guide.main_tour_product.api.tourLocation.repository;

import com.find_my_guide.main_tour_product.api.tourLocation.domain.TourLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourLocationRepository extends JpaRepository<TourLocation, Long> {

    @Query(value = "SELECT * " +
            "FROM tour_location " +
            "order by RAND() limit 100", nativeQuery = true)
    List<TourLocation> findAllRand();
}

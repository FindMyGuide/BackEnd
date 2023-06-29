package com.find_my_guide.api.tourLocation.repository;

import com.find_my_guide.api.tourLocation.domain.TourLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourLocationRepository extends JpaRepository<TourLocation, Long> {
}

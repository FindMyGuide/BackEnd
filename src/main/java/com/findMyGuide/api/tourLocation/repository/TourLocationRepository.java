package com.findMyGuide.api.tourLocation.repository;

import com.findMyGuide.api.tourLocation.domain.TourLocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourLocationRepository extends JpaRepository<TourLocation, Long> {
}

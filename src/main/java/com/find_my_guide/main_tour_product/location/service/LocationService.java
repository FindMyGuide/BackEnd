package com.find_my_guide.main_tour_product.location.service;

import com.find_my_guide.main_tour_product.location.domain.Location;
import com.find_my_guide.main_tour_product.location.dto.LocationRequest;
import com.find_my_guide.main_tour_product.location.dto.LocationResponse;
import com.find_my_guide.main_tour_product.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    @Transactional
    public LocationResponse register(LocationRequest locationRequest){
        return new LocationResponse(locationRepository.save(locationRequest.toLocation()));
    }

    @Transactional
    public LocationResponse deleteSelfTourLocationResponse(Long selfTourLocationId) {
        Location location = locationRepository.findById(selfTourLocationId).orElseThrow();
        locationRepository.delete(location);

        return new LocationResponse(location);
    }

}

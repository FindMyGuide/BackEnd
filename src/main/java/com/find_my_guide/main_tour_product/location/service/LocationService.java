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

//    @Transactional
//    public LocationResponse updateSelfTourLocation(Long selfTourLocationId, LocationRequest locationRequest) {
//        Location location = locationRepository.findById(selfTourLocationId).orElseThrow();
//
//        location.update(new Title(locationRequest.getTitle()), locationRequest.getMapX(), locationRequest.getMapY());
//
//        return new LocationResponse(locationRepository.save(location));
//    }

    @Transactional
    public LocationResponse deleteSelfTourLocationResponse(Long selfTourLocationId) {
        Location location = locationRepository.findById(selfTourLocationId).orElseThrow();
        locationRepository.delete(location);

        return new LocationResponse(location);
    }


//    public LocationResponse findByXY(LocationRequest locationRequest) {
//        // 있으면 있는거 호출 없으면 저장하고 호출
//
//        Location location = locationRepository.findLocationByMapXAndMapY(locationRequest.getMapX(), locationRequest.getMapY());
//        if (location.equals(null)) {
//            return register(locationRequest);
//        } else {
//            return new LocationResponse(location);
//        }
//    }
}

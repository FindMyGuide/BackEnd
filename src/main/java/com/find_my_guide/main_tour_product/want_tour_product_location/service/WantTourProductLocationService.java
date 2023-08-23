package com.find_my_guide.main_tour_product.want_tour_product_location.service;

import com.find_my_guide.main_tour_product.location.domain.Location;
import com.find_my_guide.main_tour_product.location.repository.LocationRepository;
import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import com.find_my_guide.main_tour_product.want_tour_product.repository.WantTourProductRepository;
import com.find_my_guide.main_tour_product.want_tour_product_location.domain.WantTourProductLocation;
import com.find_my_guide.main_tour_product.want_tour_product_location.dto.WantTourProductLocationRequest;
import com.find_my_guide.main_tour_product.want_tour_product_location.dto.WantTourProductLocationResponse;
import com.find_my_guide.main_tour_product.want_tour_product_location.repository.WantTourProductLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WantTourProductLocationService {

    private final WantTourProductLocationRepository wantTourProductLocationRepository;
    private final LocationRepository locationRepository;
    private final WantTourProductRepository wantTourProductRepository;

    @Transactional
    public WantTourProductLocationResponse addLocationToWantTourProduct(WantTourProductLocationRequest wantTourProductLocationRequest) {
        Location location = findLocation(wantTourProductLocationRequest);
        WantTourProduct wantTourProduct = findWantTourProduct(wantTourProductLocationRequest);
        WantTourProductLocation wantTourProductLocation = WantTourProductLocation.builder()
                .wantTourProduct(wantTourProduct)
                .location(location)
                .build();

        return new WantTourProductLocationResponse(wantTourProductLocationRepository.save(wantTourProductLocation));
    }

    private Location findLocation(WantTourProductLocationRequest wantTourProductLocationRequest) {
        return locationRepository.findById(wantTourProductLocationRequest.getLocationId())
                .orElseThrow(() -> new IllegalArgumentException("이 여행지는 없습니다."));
    }

    private WantTourProduct findWantTourProduct(WantTourProductLocationRequest wantTourProductLocationRequest) {
        return wantTourProductRepository.findById(wantTourProductLocationRequest.getWantTourProductId())
                .orElseThrow(() -> new IllegalArgumentException("이 요청투어는 존재하지 않습니다."));
    }
}

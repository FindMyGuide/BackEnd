package com.find_my_guide.main_tour_product.self_tour_location.service;

import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.self_tour_location.domain.SelfTourLocation;
import com.find_my_guide.main_tour_product.self_tour_location.dto.SelfTourLocationRequest;
import com.find_my_guide.main_tour_product.self_tour_location.dto.SelfTourLocationResponse;
import com.find_my_guide.main_tour_product.self_tour_location.repository.SelfTourLocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SelfTourLocationService {

    private final SelfTourLocationRepository selfTourLocationRepository;

    @Transactional
    public SelfTourLocationResponse register(SelfTourLocationRequest selfTourLocationRequest){
        return new SelfTourLocationResponse(selfTourLocationRepository.save(selfTourLocationRequest.toSelfTourLocation()));
    }

    @Transactional
    public SelfTourLocationResponse updateSelfTourLocation(Long selfTourLocationId, SelfTourLocationRequest selfTourLocationRequest) {
        SelfTourLocation selfTourLocation = selfTourLocationRepository.findById(selfTourLocationId).orElseThrow();

        selfTourLocation.update(new Title(selfTourLocationRequest.getTitle()), selfTourLocationRequest.getMapX(), selfTourLocationRequest.getMapY());

        return new SelfTourLocationResponse(selfTourLocationRepository.save(selfTourLocation));
    }

    @Transactional
    public SelfTourLocationResponse deleteSelfTourLocationResponse(Long selfTourLocationId) {
        SelfTourLocation selfTourLocation = selfTourLocationRepository.findById(selfTourLocationId).orElseThrow();
        selfTourLocationRepository.delete(selfTourLocation);

        return new SelfTourLocationResponse(selfTourLocation);
    }
}

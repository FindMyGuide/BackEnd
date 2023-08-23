package com.find_my_guide.main_tour_product.service;

import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.location.domain.Location;
import com.find_my_guide.main_tour_product.location.dto.LocationRequest;
import com.find_my_guide.main_tour_product.location.dto.LocationResponse;
import com.find_my_guide.main_tour_product.location.repository.LocationRepository;
import com.find_my_guide.main_tour_product.location.service.LocationService;
import com.find_my_guide.main_tour_product.want_reservation_date.domain.WantReservationDate;
import com.find_my_guide.main_tour_product.want_reservation_date.dto.WantReservationDateRequest;
import com.find_my_guide.main_tour_product.want_reservation_date.repository.WantReservationDateRepository;
import com.find_my_guide.main_tour_product.want_reservation_date.service.WantReservationDateService;
import com.find_my_guide.main_tour_product.want_tour_product.dto.WantTourProductRequest;
import com.find_my_guide.main_tour_product.want_tour_product.repository.WantTourProductRepository;
import com.find_my_guide.main_tour_product.want_tour_product.service.WantTourProductService;
import com.find_my_guide.main_tour_product.want_tour_product_location.domain.WantTourProductLocation;
import com.find_my_guide.main_tour_product.want_tour_product_location.repository.WantTourProductLocationRepository;
import com.find_my_guide.main_tour_product.want_tour_product_location.service.WantTourProductLocationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class WantTourProductTest {

    @InjectMocks
    @Autowired
    WantTourProductService wantTourProductService;

    @Mock
    WantTourProductRepository wantTourProductRepository;

    @Mock
    @Autowired
    WantReservationDateService wantReservationDateService;

    @Mock
    WantReservationDateRepository wantReservationDateRepository;

    @Mock
    @Autowired
    WantTourProductLocationService wantTourProductLocationService;

    @Mock
    WantTourProductLocationRepository wantTourProductLocationRepository;

    @Mock
    @Autowired
    LocationService locationService;

    @Mock
    LocationRepository locationRepository;

    @Test
    @DisplayName("원하는 투어 저장")
    void register() {

        // Given
        LocationRequest locationRequest = new LocationRequest(1L, "test1", new BigDecimal(1), new BigDecimal(1));

        Location location = locationRequest.toSelfTourLocation();

        when(locationRepository.save(any(Location.class))).thenReturn(location);

        Location saveLocation = locationRepository.save(location);

        System.out.println(saveLocation.getTitle().getTitle());

        WantReservationDateRequest wantReservationDateRequest = new WantReservationDateRequest("2023-02-03");
        WantReservationDate wantReservationDate = wantReservationDateRequest.toWantReservationDate();

        when(wantReservationDateRepository.save(any(WantReservationDate.class))).thenReturn(wantReservationDate);

        WantReservationDate saveWantReservationDate = wantReservationDateRepository.save(wantReservationDate);

        System.out.println(saveWantReservationDate.getDate());


    }
}

package com.find_my_guide.main_tour_product.service;

import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.location.domain.Location;
import com.find_my_guide.main_tour_product.location.repository.LocationRepository;
import com.find_my_guide.main_tour_product.location.service.LocationService;
import org.junit.jupiter.api.Assertions;
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
public class LocationServiceTest {

    @InjectMocks
    @Autowired
    LocationService locationService;

    @Mock
    LocationRepository locationRepository;


    @Test
    void register() {

        // Given
        Location location = Location.builder()
                .locationId(1L)
                .title(new Title("TEST_LOCATION"))
                .mapX(new BigDecimal(1))
                .mapY(new BigDecimal(1))
                .build();

        when(locationRepository.save(any(Location.class))).thenReturn(location);

        // When
        Location saveLocation = locationRepository.save(location);

        // Then
        Assertions.assertNotNull(saveLocation.getLocationId());
        Assertions.assertEquals(location.getTitle(), saveLocation.getTitle());
    }
}

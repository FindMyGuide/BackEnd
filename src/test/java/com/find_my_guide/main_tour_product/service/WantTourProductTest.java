package com.find_my_guide.main_tour_product.service;

import com.find_my_guide.main_tour_product.want_tour_product.service.WantTourProductService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class WantTourProductTest {

    @InjectMocks
    @Autowired
    WantTourProductService wantTourProductService;
}

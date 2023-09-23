package com.find_my_guide.main_tour_product.api.restaurantImage.controller;

import com.find_my_guide.main_tour_product.api.restaurantImage.service.RestaurantImageService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurant-image")
@Api
public class RestaurantImageController {

    private final RestaurantImageService restaurantImageService;

    @GetMapping("/save/restaurant")
    @ResponseBody
    public String restaurantApi() {
        return restaurantImageService.getApi();
    }
}

package com.find_my_guide.main_tour_product.api.restaurant.controller;

import com.find_my_guide.main_tour_product.api.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/restaurant")
    @ResponseBody
    public String restaurantApi() {
        return restaurantService.getApi();
    }

}

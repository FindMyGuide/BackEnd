package com.find_my_guide.main_tour_product.api.restaurant.controller;

import com.find_my_guide.main_tour_product.api.restaurant.dto.RestaurantResponse;
import com.find_my_guide.main_tour_product.api.restaurant.service.RestaurantService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@Api
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/save/restaurant")
    @ResponseBody
    public String restaurantApi() {
        return restaurantService.getApi();
    }

    @GetMapping("/restaurants")
    public ResponseEntity<List<RestaurantResponse>> restaurants(){
        return ResponseEntity.ok(restaurantService.restaurantResponses());
    }

}

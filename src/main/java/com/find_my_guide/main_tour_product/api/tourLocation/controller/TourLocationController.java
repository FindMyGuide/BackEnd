package com.find_my_guide.main_tour_product.api.tourLocation.controller;

import com.find_my_guide.main_tour_product.api.tourLocation.service.TourLocationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@Api
public class TourLocationController {

    private final TourLocationService tourLocationService;

    @GetMapping("/TourLocationApi")
    @ResponseBody
    public String TourLocationResult() {
        return tourLocationService.getApi();
    }
}

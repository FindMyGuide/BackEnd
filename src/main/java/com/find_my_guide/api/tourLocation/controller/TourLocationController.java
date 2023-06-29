package com.find_my_guide.api.tourLocation.controller;

import com.find_my_guide.api.tourLocation.service.TourLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class TourLocationController {

    private final TourLocationService tourLocationService;

    @GetMapping("/TourLocationApi")
    @ResponseBody
    public String TourLocationResult() {
        return tourLocationService.getApi();
    }
}

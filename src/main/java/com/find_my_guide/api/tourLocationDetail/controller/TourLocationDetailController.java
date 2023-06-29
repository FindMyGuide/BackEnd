package com.find_my_guide.api.tourLocationDetail.controller;

import com.find_my_guide.api.tourLocationDetail.service.TourLocationDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class TourLocationDetailController {

    private final TourLocationDetailService tourLocationDetailService;

    @GetMapping("/tourLocationDetail/{tourLocationId}")
    @ResponseBody
    public String tourLocationDetailResult(@PathVariable String tourLocationId) {
        return tourLocationDetailService.getApi(tourLocationId);
    }
}

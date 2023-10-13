package com.find_my_guide.main_tour_product.api.tourLocationDetail.controller;

import com.find_my_guide.main_tour_product.api.tourLocationDetail.dto.TourLocationDetailResponse;
import com.find_my_guide.main_tour_product.api.tourLocationDetail.service.TourLocationDetailService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@Api
public class TourLocationDetailController {

    private final TourLocationDetailService tourLocationDetailService;

    @GetMapping("/tourLocationDetail/{tourLocationId}")
    public ResponseEntity<TourLocationDetailResponse> tourLocationDetailResult(@PathVariable Long tourLocationId) {
        return ResponseEntity.ok(tourLocationDetailService.getApi(tourLocationId));
    }
}

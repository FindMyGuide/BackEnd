package com.find_my_guide.main_tour_product.api.festival.controller;
import com.find_my_guide.main_tour_product.api.festival.domain.Festival;
import com.find_my_guide.main_tour_product.api.festival.dto.FestivalResponse;
import com.find_my_guide.main_tour_product.api.festival.service.FestivalService;
import com.find_my_guide.main_tour_product.api.festivalDetail.service.FestivalDetailService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api
public class FestivalController {

    private final FestivalService festivalService;
    private final FestivalDetailService festivalDetailService;

    @GetMapping("/festival") //TODO url에 대문자 제거
    @ResponseBody
    public List<String> FestivalResult() {
        return festivalService.getApi();
    }

    @GetMapping("/festival/recommend")
    public ResponseEntity<List<FestivalResponse>> festivalRecommend() {

        List<Festival> festivals = festivalService.allFestival();
        List<FestivalResponse> responses = new ArrayList<>();
        for (Festival festival :
                festivals) {

            if (festivalDetailService.checkFestivalEnd(festival.getId())) {
                Boolean progress = festivalDetailService.checkProgress(festival.getId());
                responses.add(new FestivalResponse(festival, progress));
            }
        }

        return ResponseEntity.ok(responses);
    }
}

package com.find_my_guide.main_tour_product.api.festivalDetail.controller;

import com.find_my_guide.main_tour_product.api.festivalDetail.dto.FestivalDetailResponse;
import com.find_my_guide.main_tour_product.api.festivalDetail.service.FestivalDetailService;
import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api
public class FestivalDetailController {

    private final FestivalDetailService festivalDetailService;

//    @GetMapping("/festivalDetail/{festivalId}")
//    @ResponseBody
//    public String festivalDetailResult(@PathVariable String festivalId) {
//        return festivalDetailService.getApi(festivalId);
//    }

    @GetMapping("/festivalDetail/DB")
    @ResponseBody
    public String festivalDetailAllResult() {
        festivalDetailService.allGetApi();
        return "성공적";
    }

    @GetMapping("/festivalDetail/{festival-id}")
    public ResponseEntity<FestivalDetailResponse> festivalDetail(@PathVariable("festival-id") Long id) {

        return ResponseEntity.ok(festivalDetailService.getApi(id));
    }


}

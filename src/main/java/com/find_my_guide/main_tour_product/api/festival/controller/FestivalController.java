package com.find_my_guide.main_tour_product.api.festival.controller;
import com.find_my_guide.main_tour_product.api.festival.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FestivalController {

    private final FestivalService festivalService;

    @GetMapping("/festival") //TODO url에 대문자 제거
    @ResponseBody
    public List<String> FestivalResult() {
        return festivalService.getApi();
    }
}

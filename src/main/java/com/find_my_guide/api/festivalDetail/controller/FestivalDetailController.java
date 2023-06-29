package com.find_my_guide.api.festivalDetail.controller;

import com.find_my_guide.api.festivalDetail.service.FestivalDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class FestivalDetailController {

    private final FestivalDetailService festivalDetailService;

    @GetMapping("/festivalDetail/{festivalId}")
    @ResponseBody
    public String festivalDetailResult(@PathVariable String festivalId) {
        return festivalDetailService.getApi(festivalId);
    }
}

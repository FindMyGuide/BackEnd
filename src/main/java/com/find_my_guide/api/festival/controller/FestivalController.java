package com.find_my_guide.api.festival.controller;
import com.find_my_guide.api.festival.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FestivalController {

    private final FestivalService festivalService;

    @GetMapping("/Festival")
    @ResponseBody
    public String FestivalResult() {
        return festivalService.getApi();
    }
}

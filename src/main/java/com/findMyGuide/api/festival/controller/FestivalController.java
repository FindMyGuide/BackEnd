package com.findMyGuide.api.festival.controller;
import com.findMyGuide.api.festival.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class FestivalController {

    private final FestivalService festivalService;

    @GetMapping("/Festival")
    @ResponseBody
    public String FestivalResult() {
        return festivalService.getApi();
    }
}

package com.find_my_guide.main_tour_product.theme.controller;

import com.find_my_guide.main_tour_product.theme.service.ThemeService;
import com.find_my_guide.main_tour_product.theme.dto.ThemeRequest;
import com.find_my_guide.main_tour_product.theme.dto.ThemeResponse;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Api
public class ThemeController {

    private final ThemeService themeService;

    @PostMapping("/postTheme")
    public ResponseEntity<ThemeResponse> registerTheme(@RequestBody ThemeRequest themeRequest){
        ThemeResponse register = themeService.register(themeRequest);
        return ResponseEntity.ok(register);
    }
}

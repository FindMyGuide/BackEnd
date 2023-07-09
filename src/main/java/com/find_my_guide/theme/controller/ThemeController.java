package com.find_my_guide.theme.controller;

import com.find_my_guide.theme.dto.ThemeRequest;
import com.find_my_guide.theme.dto.ThemeResponse;
import com.find_my_guide.theme.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeService themeService;

    @PostMapping("/postTheme")
    public ResponseEntity<ThemeResponse> registerTheme(@RequestBody ThemeRequest themeRequest){
        ThemeResponse register = themeService.register(themeRequest);
        return ResponseEntity.ok(register);
    }
}

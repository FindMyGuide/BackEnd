package com.find_my_guide.theme.service;

import com.find_my_guide.theme.domain.Theme;
import com.find_my_guide.theme.domain.ThemeTitle;
import com.find_my_guide.theme.dto.ThemeRequest;
import com.find_my_guide.theme.dto.ThemeResponse;
import com.find_my_guide.theme.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository themeRepository;

    @Transactional
    public ThemeResponse register(ThemeRequest themeRequest) {
        return new ThemeResponse(themeRepository.save(themeRequest.toTheme()));

    }

    @Transactional
    public ThemeResponse updateTheme(Long themeId, ThemeRequest themeRequest) {
        Theme theme = themeRepository.findById(themeId).orElseThrow();

        theme.update(new ThemeTitle(themeRequest.getThemeTitle()));

        return new ThemeResponse(theme);

    }

    @Transactional
    public ThemeResponse deleteTheme(Long themeId) {
        Theme theme = themeRepository.findById(themeId).orElseThrow();
        themeRepository.delete(theme);

        return new ThemeResponse(theme);
    }

}

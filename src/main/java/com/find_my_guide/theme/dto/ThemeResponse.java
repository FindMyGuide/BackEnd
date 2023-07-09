package com.find_my_guide.theme.dto;

import com.find_my_guide.theme.domain.Theme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ThemeResponse {

    private String title;

    public ThemeResponse(Theme theme) {
        this.title = theme.getTitle().getTitle();
    }
}

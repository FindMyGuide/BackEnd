package com.find_my_guide.theme.dto;

import com.find_my_guide.common.validation_field.Title;
import com.find_my_guide.theme.domain.Theme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ThemeRequest {

    private Long themeId;
    private String themeTitle;

    public Theme toTheme() {
        return Theme.builder()
                .id(themeId)
                .title(new Title(themeTitle))
                .build();
    }

}

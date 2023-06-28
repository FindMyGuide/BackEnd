package com.find_my_guide.theme.dto;

import com.find_my_guide.theme.domain.Theme;
import com.find_my_guide.theme.domain.ThemeTitle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ThemeRequest {

    private String themeTitle;

    public Theme toTheme(){
        return Theme.builder()
                .themeTitle(new ThemeTitle(themeTitle))
                .build();
    }

}

package com.findMyGuide.theme.domain;

import com.findMyGuide.tour_product_theme.domain.TourProductTheme;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String themeTitle;

    @OneToMany(mappedBy = "theme")
    private List<TourProductTheme> tourProductThemes = new ArrayList<>();

}

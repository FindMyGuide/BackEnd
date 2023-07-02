package com.find_my_guide.theme.domain;

import com.find_my_guide.common.validation_field.Title;
import com.find_my_guide.tour_product_theme.domain.TourProductTheme;
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

    @Embedded
    private Title title;

    @OneToMany(mappedBy = "theme")
    private List<TourProductTheme> tourProductThemes = new ArrayList<>();

    public void update(Title title){
        this.title = title;
    }

}
package com.find_my_guide.tour_product.domain;

import com.find_my_guide.tour_product_theme.domain.TourProductTheme;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class TourProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tourProductId;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "tourProduct")
    private List<TourProductTheme> tourProductThemes = new ArrayList<>();


}

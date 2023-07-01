package com.find_my_guide.tour_product.domain;

import com.find_my_guide.common.domain.BaseEntity;
import com.find_my_guide.common.validation_field.Content;
import com.find_my_guide.common.validation_field.Title;
import com.find_my_guide.tour_product_review.domain.TourProductReview;
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
public class TourProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tourProduct_id")
    private Long tourProductId;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @OneToMany(mappedBy = "tourProduct")
    private List<TourProductTheme> tourProductThemes = new ArrayList<>();

    @OneToMany(mappedBy = "tourProduct")
    private List<TourProductReview> tourProductReviews = new ArrayList<>();

    public void update(Title title, Content content) {
        this.title = title;
        this.content = content;

    }



}

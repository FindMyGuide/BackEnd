package com.find_my_guide.tour_product_review.domain;

import com.find_my_guide.common.domain.BaseEntity;
import com.find_my_guide.common.validation_field.Content;
import com.find_my_guide.tour_product.domain.TourProduct;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourProductReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Content content;

    @ManyToOne
    @JoinColumn(name = "tourProduct_id")
    private TourProduct tourProduct;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourProductReview that = (TourProductReview) o;
        return Objects.equals(id, that.id) && Objects.equals(content, that.content) && Objects.equals(tourProduct, that.tourProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, tourProduct);
    }
}

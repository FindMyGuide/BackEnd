package com.find_my_guide.tour_product_review.domain;

import com.find_my_guide.common.domain.BaseEntity;
import com.find_my_guide.common.validation_field.Content;
import com.find_my_guide.tour_product.domain.TourProduct;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourProductReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Content content;

    @Embedded
    private Rating rating;

    @Column(length = 2000)  // url 로 저장 할 것인지 ? @BLOB 으로 이미지 저장할 것인지
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "tourProduct_id")
    private TourProduct tourProduct;

    public void addReview(TourProduct tourProduct){
        this.tourProduct = tourProduct;
        if (!this.tourProduct.getTourProductReviews().contains(this)) {
            this.tourProduct.getTourProductReviews().add(this);
        } else {
            throw new IllegalArgumentException("이미 존재하는 리뷰입니다.");
        }
    }

}

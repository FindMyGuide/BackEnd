package com.find_my_guide.main_tour_product.tour_product_review.domain;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.common.domain.BaseEntity;
import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import lombok.*;

import javax.persistence.*;

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

    @Column(length = 2000)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "tourProduct_id")
    private TourProduct tourProduct;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private Boolean isWritten;

    public void addReview(TourProduct tourProduct){
        this.tourProduct = tourProduct;
        if (!this.tourProduct.getTourProductReviews().contains(this)) {
            this.tourProduct.getTourProductReviews().add(this);
        } else {
            throw new IllegalArgumentException("이미 존재하는 리뷰입니다.");
        }
    }

    public void update(Content content, Rating rating, String imageUrl){
        this.content = content;
        this.rating = rating;
        this.imageUrl = imageUrl;
    }

    public void setMember(Member member){
        this.member = member;
    }

    public void setIsWritten(Boolean isWritten) {
        this.isWritten = isWritten;
    }
}

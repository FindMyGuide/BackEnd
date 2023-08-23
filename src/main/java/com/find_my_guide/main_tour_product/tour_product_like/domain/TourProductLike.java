package com.find_my_guide.main_tour_product.tour_product_like.domain;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourProductLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tourProduct_id")
    private TourProduct tourProduct;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

}

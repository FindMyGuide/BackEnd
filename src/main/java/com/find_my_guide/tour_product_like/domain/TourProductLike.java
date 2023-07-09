package com.find_my_guide.tour_product_like.domain;

import com.find_my_guide.tour_product.domain.TourProduct;
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


    // 추후 MemberId 추가하기

}

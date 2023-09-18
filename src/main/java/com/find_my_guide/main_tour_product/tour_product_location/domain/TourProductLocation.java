package com.find_my_guide.main_tour_product.tour_product_location.domain;

import com.find_my_guide.main_tour_product.location.domain.Location;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
public class TourProductLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tourProduct_id")
    private TourProduct tourProduct;

    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;
}

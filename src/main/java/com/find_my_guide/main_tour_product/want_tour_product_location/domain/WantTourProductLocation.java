package com.find_my_guide.main_tour_product.want_tour_product_location.domain;

import com.find_my_guide.main_tour_product.location.domain.Location;
import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class WantTourProductLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "want_tour_product_id")
    private WantTourProduct wantTourProduct;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;


}

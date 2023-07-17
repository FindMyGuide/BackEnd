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
    private WantTourProduct wantTourProduct;

    @ManyToOne
    private Location location;

    public void saveWantTourProduct(WantTourProduct wantTourProduct) {
        this.wantTourProduct = wantTourProduct;
    }

    public void saveLocation(Location location) {
        this.location = location;
    }
}
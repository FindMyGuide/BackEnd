package com.find_my_guide.main_tour_product.want_tour_product_theme.domain;

import com.find_my_guide.main_tour_product.theme.domain.Theme;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class WantTourProductTheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private WantTourProduct wantTourProduct;

    @ManyToOne
    private Theme theme;


    public void saveTourProduct(WantTourProduct wantTourProduct){
        this.wantTourProduct = wantTourProduct;
    }

    public void saveTheme(Theme theme){
        this.theme = theme;
    }
}

package com.findMyGuide.tour_product_theme.domain;

import com.findMyGuide.theme.domain.Theme;
import com.findMyGuide.tour_product.domain.TourProduct;
import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class TourProductTheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TourProduct tourProduct;

    @ManyToOne
    private Theme theme;


    public void saveTourProduct(TourProduct tourProduct){
        this.tourProduct = tourProduct;
    }

    public void saveTheme(TourProduct tourProduct){
        this.tourProduct = tourProduct;
    }
}

package com.find_my_guide.main_tour_product.tour_product.domain;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imageUrl;

    @ManyToOne
    @JsonIgnore
    private TourProduct tourProduct;

    public Images(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

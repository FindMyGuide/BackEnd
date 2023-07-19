package com.find_my_guide.main_tour_product.location.domain;

import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.want_tour_product_location.domain.WantTourProductLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long locationId;

    @Embedded
    private Title title;

    private BigDecimal mapX;
    private BigDecimal mapY;

    @OneToMany(mappedBy = "location")
    private List<WantTourProductLocation> wantTourProductLocations = new ArrayList<>();

    public void update(Title title, BigDecimal mapX, BigDecimal mapY) {
        this.title = title;
        this.mapX = mapX;
        this.mapY = mapY;
    }
}

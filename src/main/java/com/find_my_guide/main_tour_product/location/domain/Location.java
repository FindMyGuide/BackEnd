package com.find_my_guide.main_tour_product.location.domain;

import com.find_my_guide.main_tour_product.common.validation_field.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long selfTourLocationId;

    @Embedded
    private Title title;

    private String mapX;
    private String mapY;

    public void update(Title title, String mapX, String mapY) {
        this.title = title;
        this.mapX = mapX;
        this.mapY = mapY;
    }
}

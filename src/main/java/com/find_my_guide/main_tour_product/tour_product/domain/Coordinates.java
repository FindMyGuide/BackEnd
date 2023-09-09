package com.find_my_guide.main_tour_product.tour_product.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {

    @Column
    private BigDecimal mapX;

    @Column
    private BigDecimal mapY;
}

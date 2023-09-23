package com.find_my_guide.main_tour_product.tour_product.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HowManyDay {

    private String night;

    private String day;
}

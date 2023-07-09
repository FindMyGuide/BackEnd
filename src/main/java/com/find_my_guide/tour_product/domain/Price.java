package com.find_my_guide.tour_product.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@Getter
@NoArgsConstructor
public class Price {

    private static final int ZERO = 0;

    @Column(nullable = false)
    private BigDecimal price;

    public Price(Long price) {
        validate(price);
        this.price = BigDecimal.valueOf(price);
    }


    private void validate(Long price) {
        validatePriceNotEmpty(price);
        validatePriceLessThenZERO(price);
    }


    private void validatePriceNotEmpty(Long price) {
        if (price == null) {
            throw new IllegalArgumentException("가격은 필수 항목입니다.");
        }
    }

    private void validatePriceLessThenZERO(Long price) {
        if (price <= ZERO) {
            throw new IllegalArgumentException("가격은 0보다 커야 합니다.");
        }
    }

}

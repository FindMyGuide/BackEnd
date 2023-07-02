package com.find_my_guide.tour_product_review.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
public class Rating {

    private static final Double ZERO = 0.0;

    private static final Double FIVE = 5.0;


    @Column(nullable = false)
    private Double rating;

    public Rating(double rating) {
        validate(rating);
        this.rating = Double.valueOf(rating);
    }

    private void validate(Double rating) {
        validateRatingLessThanZeroOverThanFive(rating);
    }

    private void validateRatingLessThanZeroOverThanFive(Double rating) {
        if (rating < ZERO && rating > FIVE) {
            throw new IllegalArgumentException("평점이 범위를 벗어 났습니다.");
        }

    }
}

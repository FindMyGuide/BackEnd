package com.find_my_guide.tour_product_review.domain;

import com.find_my_guide.theme.exception.TitleNotEmptyException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class ReviewContent {

    @Column(nullable = false)
    private String review;


    public ReviewContent(String review) {
        validate(review);
        this.review = review;
    }

    private void validate(String review) {
        validateReviewNotEmpty(review);
    }


    private void validateReviewNotEmpty(String review) {
        if (Objects.isNull(review) || review.isEmpty()) {
            throw new TitleNotEmptyException();
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewContent that = (ReviewContent) o;
        return Objects.equals(review, that.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(review);
    }
}

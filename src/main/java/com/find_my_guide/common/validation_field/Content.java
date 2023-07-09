package com.find_my_guide.common.validation_field;

import com.find_my_guide.theme.exception.TitleNotEmptyException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class Content {

    @Column(nullable = false)
    private String content;


    public Content(String content) {
        validate(content);
        this.content = content;
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
        Content that = (Content) o;
        return Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }
}

package com.find_my_guide.main_tour_product.common.validation_field;


import com.find_my_guide.main_tour_product.theme.exception.TitleNotEmptyException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class Title {

    @Column(nullable = false)
    private String title;

    public Title(String title) {
        validate(title);
        this.title = title;
    }

    private void validate(String title) {
        validateTitleNotEmpty(title);
    }

    private void validateTitleNotEmpty(String title) {
        if (Objects.isNull(title) || title.isEmpty()) {
            throw new TitleNotEmptyException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Title that = (Title) o;
        return Objects.equals(title, that.title);
    }

    public String value() {
        return this.title;
    }


    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}

package com.find_my_guide.theme.domain;


import com.find_my_guide.theme.exception.TitleNotEmptyException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@NoArgsConstructor
public class ThemeTitle {

    @Column(nullable = false)
    private String title;

    public ThemeTitle(String title) {
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
        ThemeTitle that = (ThemeTitle) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}

package com.find_my_guide.q_n_a.domain;

import com.find_my_guide.common.domain.BaseEntity;
import com.find_my_guide.common.validation_field.Content;
import com.find_my_guide.common.validation_field.Title;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class QnA extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qnaId;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    public void update(Title title, Content content) {
        this.title = title;
        this.content = content;
    }
}

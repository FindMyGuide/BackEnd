package com.find_my_guide.qna_.domain;

import com.find_my_guide.common.domain.BaseEntity;
import com.find_my_guide.common.validation_field.Content;
import com.find_my_guide.common.validation_field.Title;
import com.find_my_guide.qna_reply.domain.QnaReply;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

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

    @OneToOne(mappedBy = "qna")
    private QnaReply qnaReply;

    public void update(Title title, Content content) {
        this.title = title;
        this.content = content;
    }


    public void addQnaReply(QnaReply qnaReply) {
        if (Objects.isNull(qnaReply)) {
            throw new IllegalArgumentException("QnaReply does not exist");
        }

        qnaReply.setQna(this);
        this.qnaReply = qnaReply;
    }
}

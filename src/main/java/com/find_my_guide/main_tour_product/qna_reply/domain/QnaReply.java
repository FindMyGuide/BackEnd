package com.find_my_guide.main_tour_product.qna_reply.domain;

import com.find_my_guide.main_tour_product.common.domain.BaseEntity;
import com.find_my_guide.main_tour_product.common.validation_field.Content;
import com.find_my_guide.main_tour_product.qna_.domain.QnA;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class QnaReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qna_reply_id")
    private Long Id;

    @Embedded
    private Content content;

    @OneToOne
    @JoinColumn(name = "qna_id")
    private QnA qna;

    public void setQna(QnA qna) {
        this.qna = qna;
    }

    public void update(Content content) {
        this.content = content;
    }
}

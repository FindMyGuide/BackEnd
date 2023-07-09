package com.find_my_guide.main_tour_product.qna_.dto;

import com.find_my_guide.main_tour_product.qna_.domain.QnA;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class QnaResponse {

    private Long id;

    private String title;

    private String content;

    public QnaResponse(QnA qna) {
        this.id = qna.getQnaId();
        this.title = qna.getTitle().getTitle();
        this.content = qna.getContent().getContent();
    }
}

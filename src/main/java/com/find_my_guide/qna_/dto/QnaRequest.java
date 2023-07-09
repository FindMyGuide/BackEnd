package com.find_my_guide.qna_.dto;

import com.find_my_guide.common.validation_field.Content;
import com.find_my_guide.common.validation_field.Title;
import com.find_my_guide.qna_.domain.QnA;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QnaRequest {

    private Long qnaId;

    private String title;

    private String content;

    public QnA toQna() {
        return QnA.builder()
                .qnaId(qnaId)
                .title(new Title(title))
                .content(new Content(content))
                .build();
    }

    public QnaRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

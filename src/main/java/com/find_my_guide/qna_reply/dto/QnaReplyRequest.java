package com.find_my_guide.qna_reply.dto;

import com.find_my_guide.common.validation_field.Content;
import com.find_my_guide.qna_reply.domain.QnaReply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QnaReplyRequest {

    private Long id;

    private String content;

    public QnaReply toQnaReply() {
        return QnaReply.builder()
                .Id(id)
                .content(new Content(content))
                .build();
    }
}

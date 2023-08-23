package com.find_my_guide.main_tour_product.qna_reply.dto;


import com.find_my_guide.main_tour_product.qna_reply.domain.QnaReply;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QnaReplyResponse {

    private Long id;

    private String content;

    public QnaReplyResponse(QnaReply qnaReply) {
        this.id = qnaReply.getId();
        this.content = qnaReply.getContent().getContent();
    }
}

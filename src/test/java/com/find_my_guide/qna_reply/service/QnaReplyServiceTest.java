package com.find_my_guide.qna_reply.service;

import com.find_my_guide.common.validation_field.Content;
import com.find_my_guide.common.validation_field.Title;
import com.find_my_guide.qna_.domain.QnA;
import com.find_my_guide.qna_reply.dto.QnaReplyRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QnaReplyServiceTest {

    @Autowired
    QnaReplyService qnaReplyService;

    QnA qnA;


    @BeforeEach
    void BeforeEach() {

        qnA = QnA.builder()
                .qnaId(1L)
                .title(new Title("test"))
                .content(new Content("test"))
                .build();

    }


    @Test
    @DisplayName("Qna_Reply 등록")
    void register() {

        QnaReplyRequest qnaReplyRequest = new QnaReplyRequest(1L, "content");

        qnaReplyService.register(1L, qnaReplyRequest);
    }

    @Test
    void update() {
    }
}
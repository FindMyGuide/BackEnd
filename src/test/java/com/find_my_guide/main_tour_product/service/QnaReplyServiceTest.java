package com.find_my_guide.main_tour_product.service;

import com.find_my_guide.main_tour_product.qna_.dto.QnaRequest;
import com.find_my_guide.main_tour_product.qna_.dto.QnaResponse;
import com.find_my_guide.main_tour_product.qna_.service.QnaService;
import com.find_my_guide.main_tour_product.qna_reply.dto.QnaReplyRequest;
import com.find_my_guide.main_tour_product.qna_reply.dto.QnaReplyResponse;
import com.find_my_guide.main_tour_product.qna_reply.service.QnaReplyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QnaReplyServiceTest {

    @Autowired
    QnaReplyService qnaReplyService;

    @Autowired
    QnaService qnaService;

    QnaResponse qnaResponse;

    @BeforeEach
    void BeforeEach() {
        QnaRequest qnaRequest = new QnaRequest("Test title","Test content");
        qnaResponse = qnaService.register(qnaRequest);

    }


    @Test
    @DisplayName("Qna_Reply 등록")
    void register() {

        QnaReplyRequest qnaReplyRequest = new QnaReplyRequest(1L,"Test reply");
        QnaReplyResponse qnaReplyResponse = qnaReplyService.register(qnaResponse.getId(), qnaReplyRequest);

        assertNotNull(qnaReplyResponse);
        assertEquals(qnaReplyRequest.getContent(), qnaReplyResponse.getContent());

    }

    @Test
    @DisplayName("Qna_Reply 수정")
    void update() {
        // given: 등록된 QnaReply가 있다고 가정
        QnaReplyRequest originalRequest = new QnaReplyRequest(1L, "Original reply");
        QnaReplyResponse originalResponse = qnaReplyService.register(qnaResponse.getId(), originalRequest);

        // when: QnaReply를 수정
        QnaReplyRequest updateRequest = new QnaReplyRequest(1L, "Updated reply");
        QnaReplyResponse updatedResponse = qnaReplyService.update(qnaResponse.getId(), originalResponse.getId(), updateRequest);

        // then: 수정된 QnaReply의 내용이 요청의 내용과 일치함을 확인
        assertNotNull(updatedResponse);
        assertEquals(updateRequest.getContent(), updatedResponse.getContent());
        assertNotEquals(originalRequest.getContent(), updatedResponse.getContent());
    }
}
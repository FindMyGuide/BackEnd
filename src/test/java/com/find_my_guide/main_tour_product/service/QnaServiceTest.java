package com.find_my_guide.main_tour_product.service;

import com.find_my_guide.main_tour_product.qna_.dto.QnaRequest;
import com.find_my_guide.main_tour_product.qna_.dto.QnaResponse;
import com.find_my_guide.main_tour_product.qna_.service.QnaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QnaServiceTest {

    @Autowired
    QnaService qnaService;

    @Test
    void register() {




    }

    @Test
    void update() {

        QnaRequest qnaRequest = new QnaRequest("title1","content1");
        QnaResponse register = qnaService.register(qnaRequest);
        QnaRequest qnaRequest2 = new QnaRequest("new","content1new");

        qnaService.update(1L,qnaRequest2);
    }

    @Test
    void delete() {
    }

    @Test
    void detail() {
    }
}
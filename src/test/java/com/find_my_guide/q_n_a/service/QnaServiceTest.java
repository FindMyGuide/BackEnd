package com.find_my_guide.q_n_a.service;

import com.find_my_guide.q_n_a.dto.QnaRequest;
import com.find_my_guide.q_n_a.dto.QnaResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

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
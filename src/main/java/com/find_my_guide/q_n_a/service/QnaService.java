package com.find_my_guide.q_n_a.service;

import com.find_my_guide.common.validation_field.Content;
import com.find_my_guide.common.validation_field.Title;
import com.find_my_guide.q_n_a.domain.QnA;
import com.find_my_guide.q_n_a.dto.QnaRequest;
import com.find_my_guide.q_n_a.dto.QnaResponse;
import com.find_my_guide.q_n_a.repository.QnaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QnaService {

    private final QnaRepository qnaRepository;

    @Transactional
    public QnaResponse register(QnaRequest qnaRequest) {
        return new QnaResponse(qnaRepository.save(qnaRequest.toQna()));
    }

    @Transactional
    public QnaResponse update(Long id, QnaRequest qnaRequest) {
        QnA qnA = findById(id);
        qnA.update(new Title(qnaRequest.getTitle()), new Content(qnaRequest.getContent()));

        return new QnaResponse(qnaRepository.save(qnA));

    }

    @Transactional
    public QnaResponse delete(Long id) {
        QnA qna = findById(id);
        qnaRepository.delete(qna);
        return new QnaResponse(qna);
    }

    public QnaResponse detail(Long id) {
        return new QnaResponse(findById(id));
    }

    private QnA findById(Long id) {
        return qnaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 QnA 입니다."));
    }
}

package com.find_my_guide.qna_reply.service;

import com.find_my_guide.common.validation_field.Content;
import com.find_my_guide.qna_.domain.QnA;
import com.find_my_guide.qna_.repository.QnaRepository;
import com.find_my_guide.qna_reply.domain.QnaReply;
import com.find_my_guide.qna_reply.dto.QnaReplyRequest;
import com.find_my_guide.qna_reply.dto.QnaReplyResponse;
import com.find_my_guide.qna_reply.repository.QnaReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QnaReplyService {

    private final QnaReplyRepository qnaReplyRepository;

    private final QnaRepository qnaRepository;

    @Transactional
    public QnaReplyResponse register(Long postId, QnaReplyRequest qnaReplyRequest) {
        QnA qnA =findQnaById(postId);

        QnaReply qnaReply = qnaReplyRequest.toQnaReply();

        if (Objects.isNull(qnaReply)) {
            throw new IllegalArgumentException("QnaReply does not exist");
        }

        qnA.addQnaReply(qnaReply);

        return new QnaReplyResponse(qnaReplyRepository.save(qnaReply));
    }

    @Transactional
    public QnaReplyResponse update(Long postId, Long replyId, QnaReplyRequest qnaReplyRequest) {
        findQnaById(postId);

        QnaReply reply = findReplyById(replyId);

        matchQnaId_ReplyId(postId, reply);

        reply.update(new Content(qnaReplyRequest.getContent()));

        return new QnaReplyResponse(qnaReplyRepository.save(reply));
    }

    private void matchQnaId_ReplyId(Long postId, QnaReply reply) {
        if (!reply.getQna().getQnaId().equals(postId)) {
            throw new IllegalArgumentException("Reply does not belong to th Qna");
        }
    }

    private QnaReply findReplyById(Long qnaId) {
        QnaReply reply = qnaReplyRepository.findById(qnaId)
                .orElseThrow(() -> new IllegalArgumentException("Qna does not exist"));
        return reply;
    }

    private QnA findQnaById(Long id) {
        return qnaRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("존재 하지 않는 Qna입니다."));
    }
}

package com.find_my_guide.main_tour_product.qna_reply.repository;

import com.find_my_guide.main_tour_product.qna_reply.domain.QnaReply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaReplyRepository extends JpaRepository<QnaReply, Long> {
}

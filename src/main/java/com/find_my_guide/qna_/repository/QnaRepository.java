package com.find_my_guide.qna_.repository;

import com.find_my_guide.qna_.domain.QnA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<QnA, Long> {
}

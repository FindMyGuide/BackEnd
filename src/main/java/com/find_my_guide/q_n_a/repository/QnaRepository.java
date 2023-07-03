package com.find_my_guide.q_n_a.repository;

import com.find_my_guide.q_n_a.domain.QnA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<QnA, Long> {
}

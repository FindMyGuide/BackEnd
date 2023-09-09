package com.find_my_guide.main_member.guideLike.repository;

import com.find_my_guide.main_member.guideLike.domain.GuideLike;
import com.find_my_guide.main_member.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuideLikeRepository extends JpaRepository<GuideLike,Long> {
    Optional<GuideLike> findByGuideAndMember(Member guide, Member member);

}

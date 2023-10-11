package com.find_my_guide.main_member.guideLike.repository;

import com.find_my_guide.main_member.guideLike.domain.GuideLike;
import com.find_my_guide.main_member.member.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GuideLikeRepository extends JpaRepository<GuideLike,Long> {
    Optional<GuideLike> findByGuideAndMember(Member guide, Member member);

    @Query("SELECT g.guide FROM GuideLike g WHERE g.member = :member")
    List<Member> findAllGuidesByMember(@Param("member") Member member);

}

package com.find_my_guide.main_member.member.repository;

import com.find_my_guide.main_member.member.domain.entity.Member;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByName(String name);

    Optional<Member> findByPhoneNumber(String phoneNumber);

    Optional<Member> findByRefreshToken(String refreshToken);

    @Query("SELECT g.guide FROM TourHistoryManager g GROUP BY g.guide ORDER BY COUNT(g.id) DESC")
    List<Member> findTop10PopularGuides(Pageable pageable);

    @Query("SELECT g.guide FROM TourHistoryManager g GROUP BY g.guide")
    List<Member> findAllGuides();

    Optional<Member> findByKakaoId(String kakaoId);
    boolean existsByRefreshToken(String refreshToken);

    void deleteByIsEmailVerifiedFalse();
}

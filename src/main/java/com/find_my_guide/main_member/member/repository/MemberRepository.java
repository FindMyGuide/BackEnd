package com.find_my_guide.main_member.member.repository;

import com.find_my_guide.main_member.member.domain.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String nickname);

    Optional<Member> findByPhoneNumber(String phoneNumber);

    Optional<Member> findByRefreshToken(String refreshToken);
    boolean existsByRefreshToken(String refreshToken);
}

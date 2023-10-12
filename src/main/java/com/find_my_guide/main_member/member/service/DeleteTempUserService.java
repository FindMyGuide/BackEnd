package com.find_my_guide.main_member.member.service;

import com.find_my_guide.main_member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteTempUserService {

    private final MemberRepository memberRepository;

    @Transactional
    @Scheduled(fixedRate = 300000)
    public void deleteUnverifiedUsers() {
        memberRepository.deleteByIsEmailVerifiedFalse();
    }
}

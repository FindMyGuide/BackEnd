package com.find_my_guide.main_member.guideLike.service;

import com.find_my_guide.main_member.common.NotFoundException;
import com.find_my_guide.main_member.guideLike.domain.GuideLike;
import com.find_my_guide.main_member.guideLike.repository.GuideLikeRepository;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import com.find_my_guide.main_member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuideLikeService {

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    private final GuideLikeRepository guideLikeRepository;

    public Boolean likeGuide(Long guideId, String memberEmail) {
        try {
            Member member = memberService.findByEmail(memberEmail);
            Member guide = memberRepository.findById(guideId).orElseThrow(() -> new NotFoundException("Guide not found"));

            // 좋아요 중복 체크
            GuideLike existingLike = guideLikeRepository.findByGuideAndMember(guide, member).orElseThrow();

            if (existingLike != null) {
                throw new IllegalStateException("이미 좋아요 한 가이드입니다.");
            }

            GuideLike like = GuideLike.builder()
                    .guide(guide)
                    .member(member)
                    .build();

            guideLikeRepository.save(like);

            return true;

        } catch (NotFoundException e) {
            e.getErrorCode();
            return false;
        }
    }

}

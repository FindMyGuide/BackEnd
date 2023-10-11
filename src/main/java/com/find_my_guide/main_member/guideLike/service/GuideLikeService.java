package com.find_my_guide.main_member.guideLike.service;

import com.find_my_guide.main_member.common.NotFoundException;
import com.find_my_guide.main_member.guideLike.domain.GuideLike;
import com.find_my_guide.main_member.guideLike.repository.GuideLikeRepository;
import com.find_my_guide.main_member.member.domain.dto.GuideResponse;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import com.find_my_guide.main_member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuideLikeService {

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    private final GuideLikeRepository guideLikeRepository;

    public Boolean likeGuide(Long guideId, String memberEmail) {

        Member member = memberService.findByEmail(memberEmail);
        Member guide = memberRepository.findById(guideId).orElseThrow(() -> new NotFoundException("Guide not found"));

        Optional<GuideLike> optionalExistingLike = guideLikeRepository.findByGuideAndMember(guide, member);

        if (optionalExistingLike.isPresent()) {
            throw new IllegalStateException("이미 좋아요 한 가이드입니다.");
        }

        GuideLike like = GuideLike.builder()
                .guide(guide)
                .member(member)
                .build();

        guideLikeRepository.save(like);

        return true;

    }


    public void dislikeGuide(Long guideId, String memberEmail) {
        Member member = memberService.findByEmail(memberEmail);
        Member guide = memberRepository.findById(guideId).orElseThrow(() -> new NotFoundException("가이드를 찾을 수 없음"));

        GuideLike existingLike = guideLikeRepository.findByGuideAndMember(guide, member)
                .orElseThrow(() -> new IllegalStateException("좋아요하지 않은 가이드입니다."));

        guideLikeRepository.delete(existingLike);
    }

    public List<GuideResponse> getLikedGuides(String memberEmail) {
        Member member = memberService.findByEmail(memberEmail);
        return guideLikeRepository.findAllGuidesByMember(member)
                .stream()
                .map(GuideResponse::new)
                .collect(Collectors.toList());
    }


}

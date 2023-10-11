package com.find_my_guide.main_member.guideLike.controller;

import com.find_my_guide.main_member.guideLike.service.GuideLikeService;
import com.find_my_guide.main_member.member.domain.dto.GuideResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guide-like")
public class GuideLikeController {

    private final GuideLikeService guideLikeService;

    @PostMapping("/{guideId}")
    public ResponseEntity<String> addLike(@PathVariable Long guideId, final org.springframework.security.core.Authentication authentication) {
        String memberEmail = (String) authentication.getPrincipal();
        guideLikeService.likeGuide(guideId, memberEmail);
        return ResponseEntity.ok("좋아요가 성공적으로 추가되었습니다.");
    }

    @DeleteMapping("/delete/{guideId}")
    public ResponseEntity<String> removeLike(@PathVariable Long guideId, final org.springframework.security.core.Authentication authentication) {
        String memberEmail = (String) authentication.getPrincipal();
        guideLikeService.dislikeGuide(guideId, memberEmail); // GuideLikeService에 unlikeGuide 메서드를 추가해야 합니다.
        return ResponseEntity.ok("좋아요가 성공적으로 취소되었습니다.");
    }

    @GetMapping("/guide-list")
    public ResponseEntity<List<GuideResponse>> getLikedGuides(final org.springframework.security.core.Authentication authentication) {
        String memberEmail = (String) authentication.getPrincipal();
        List<GuideResponse> likedGuides = guideLikeService.getLikedGuides(memberEmail);
        return ResponseEntity.ok(likedGuides);
    }
}

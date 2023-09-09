package com.find_my_guide.main_member.member.controller;


import com.find_my_guide.main_member.member.domain.dto.GuideResponse;
import com.find_my_guide.main_member.member.domain.entity.Gender;
import com.find_my_guide.main_member.member.service.MemberService;
import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/find-my-guide/guide")
@Api
public class GuideController {

    private final MemberService memberService;


    @GetMapping("/popular-guide")
    public ResponseEntity<List<GuideResponse>> getTop10PopularGuides() {
        return ResponseEntity.ok(memberService.getTop10PopularGuides());
    }



    @GetMapping("/guides")
    public ResponseEntity<List<GuideResponse>> findAllGuides() {
        return ResponseEntity.ok(memberService.findAllGuides());
    }

    @GetMapping("/guides/search")
    public ResponseEntity<List<GuideResponse>> findGuidesByCriteria(
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthDate,
            @RequestParam(required = false) Languages language,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        List<GuideResponse> guides = memberService.findGuideByCriteria(gender, birthDate, language, date);
        return ResponseEntity.ok(guides);
    }

    @GetMapping("/guides/detail/{guideId}")
    public ResponseEntity<GuideResponse> guideDetail(
            @PathVariable Long guideId
    ) {
        return ResponseEntity.ok(memberService.guideDetail(guideId));

    }
}

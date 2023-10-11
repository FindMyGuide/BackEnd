package com.find_my_guide.main_member.member.controller;


import com.find_my_guide.main_member.member.domain.dto.GuideResponse;
import com.find_my_guide.main_member.member.domain.entity.Gender;
import com.find_my_guide.main_member.member.service.MemberService;
import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
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



    @GetMapping("/all")
    public ResponseEntity<List<GuideResponse>> findAllGuides() {
        return ResponseEntity.ok(memberService.findAllGuides());
    }

    @GetMapping("/search")
    public ResponseEntity<List<GuideResponse>> findGuidesByCriteria(
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) String age,
            @RequestParam(required = false) Languages language,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        String[] ageTokens = age.split("-");
        if (ageTokens.length != 2) {
            return ResponseEntity.badRequest().build();
        }

        int startAge, endAge;
        try {
            startAge = Integer.parseInt(ageTokens[0].trim());
            endAge = Integer.parseInt(ageTokens[1].trim());
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }

        List<GuideResponse> guides = memberService.findGuideByCriteria(gender, startAge, endAge, language, date);
        return ResponseEntity.ok(guides);
    }

    @GetMapping("/guides/detail/{guideId}")
    public ResponseEntity<GuideResponse> guideDetail(
            @PathVariable Long guideId
    ) {
        return ResponseEntity.ok(memberService.guideDetail(guideId));

    }
}

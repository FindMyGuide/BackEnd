package com.findMyGuide.member.controller;

import com.findMyGuide.member.domain.dto.CreateMemberRequest;
import com.findMyGuide.member.domain.dto.CreateMemberResponse;
import com.findMyGuide.member.domain.dto.UpdateMemberRequest;
import com.findMyGuide.member.domain.dto.UpdateMemberResponse;
import com.findMyGuide.member.service.MemberService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/find-my-guide")
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public ResponseEntity<CreateMemberResponse> register(@RequestBody final CreateMemberRequest request) {

        CreateMemberResponse response = memberService.createMember(request);
        URI uri = URI.create("/find-my-guide/sign-up");

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateMemberResponse> update(@RequestBody final UpdateMemberRequest request) {

        UpdateMemberResponse response = memberService.updateMember(request);

        return ResponseEntity.ok(response);
    }
}

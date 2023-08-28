package com.find_my_guide.main_member.member.controller;

import com.find_my_guide.main_member.jwt.service.JwtTokenUtil;
import com.find_my_guide.main_member.member.domain.dto.*;

import java.net.URI;
import java.util.HashMap;
import javax.validation.Valid;

import com.find_my_guide.main_member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/find-my-guide")
public class MemberRestController {

    private final MemberService memberService;


    @PostMapping("/sign-up")
    public ResponseEntity<CreateMemberResponse> register(@RequestBody @Valid final CreateMemberRequest request) {

        CreateMemberResponse response = memberService.createMember(request);
        URI uri = URI.create("/find-my-guide/sign-up");

        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@RequestBody @Valid final LoginMemberRequest loginMemberRequest) {
        HashMap<String, Object> token = memberService.login(loginMemberRequest);
        if (token != null)
            return ResponseEntity.ok().body(token);
        return ResponseEntity.badRequest().body("토큰이 null");
    }

    @GetMapping("/member/detail")
    public ResponseEntity<ReadMemberResponse> read(final Authentication authentication) {
        return ResponseEntity.ok(memberService.readMember((String) authentication.getPrincipal()));
    }



    @PutMapping("/member/update")
    public ResponseEntity<UpdateMemberResponse> update(final Authentication authentication,
                                                       @RequestBody @Valid final UpdateMemberRequest request) {
        UpdateMemberResponse response = memberService.updateMember((String) authentication.getPrincipal(), request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/member/delete")
    public ResponseEntity<DeleteMemberResponse> delete(final Authentication authentication) {

        DeleteMemberResponse response = memberService.deleteMember((String) authentication.getPrincipal());

        return ResponseEntity.ok(response);
    }

}

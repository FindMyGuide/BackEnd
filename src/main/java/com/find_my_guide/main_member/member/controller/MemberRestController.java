package com.find_my_guide.main_member.member.controller;

import com.find_my_guide.main_member.member.domain.dto.*;

import java.net.URI;
import javax.validation.Valid;

import com.find_my_guide.main_member.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<CreateMemberResponse> register(@RequestBody @Valid final CreateMemberRequest request) {

        CreateMemberResponse response = memberService.createMember(request);
        URI uri = URI.create("/find-my-guide/sign-up");

        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<LoginMemberResponse> login(@RequestBody @Valid final LoginMemberRequest loginMemberRequest) {
        LoginMemberResponse login = memberService.login(loginMemberRequest);
        URI uri = URI.create("/find-my-guide/sign-in");

        return ResponseEntity.created(uri).body(login);
    }

    @GetMapping("/{email}")
    public ResponseEntity<ReadMemberResponse> read(@PathVariable("email") final String email) {

        ReadMemberResponse response = memberService.readMember(email);

        return ResponseEntity.ok(response);
    }


    @PutMapping("/update/{email}")
    public ResponseEntity<UpdateMemberResponse> update(@PathVariable("email") final String email,
                                                       @RequestBody @Valid final UpdateMemberRequest request) {

        UpdateMemberResponse response = memberService.updateMember(email, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<DeleteMemberResponse> delete(@PathVariable("email") final String email) {

        DeleteMemberResponse response = memberService.deleteMember(email);

        return ResponseEntity.ok(response);
    }
}

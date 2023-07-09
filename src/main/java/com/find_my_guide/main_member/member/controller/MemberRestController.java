package com.find_my_guide.main_member.member.controller;

import com.find_my_guide.main_member.member.domain.dto.CreateMemberRequest;
import com.find_my_guide.main_member.member.domain.dto.CreateMemberResponse;
import com.find_my_guide.main_member.member.domain.dto.DeleteMemberResponse;
import com.find_my_guide.main_member.member.domain.dto.ReadMemberResponse;
import com.find_my_guide.main_member.member.domain.dto.UpdateMemberRequest;
import com.find_my_guide.main_member.member.domain.dto.UpdateMemberResponse;
import java.net.URI;
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


    private final com.find_my_guide.main_member.member.service.MemberService memberService;
    @PostMapping("/sign-up")
    public ResponseEntity<CreateMemberResponse> register(@RequestBody final CreateMemberRequest request) {

        CreateMemberResponse response = memberService.createMember(request);
        URI uri = URI.create("/find-my-guide/sign-up");

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{email}")
    public ResponseEntity<ReadMemberResponse> read(@PathVariable("email")final String email) {

        ReadMemberResponse response = memberService.readMember(email);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<UpdateMemberResponse> update(@PathVariable("email")final String email,
                                                        @RequestBody final UpdateMemberRequest request) {

        UpdateMemberResponse response = memberService.updateMember(email ,request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<DeleteMemberResponse> delete(@PathVariable("email")final String email) {

        DeleteMemberResponse response = memberService.deleteMember(email);

        return ResponseEntity.ok(response);
    }
}

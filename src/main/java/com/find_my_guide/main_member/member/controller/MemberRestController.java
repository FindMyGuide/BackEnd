package com.find_my_guide.main_member.member.controller;

import com.find_my_guide.main_member.jwt.service.JwtTokenUtil;
import com.find_my_guide.main_member.mail.dto.MailConfirmDto;
import com.find_my_guide.main_member.member.domain.dto.*;
import com.find_my_guide.main_member.member.domain.entity.Gender;
import com.find_my_guide.main_member.member.service.MemberService;
import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/find-my-guide/member")
@Api
public class MemberRestController {

    private final MemberService memberService;


    @PostMapping("/initiate-sign-up")
    public ResponseEntity<String> initiateSignUp(@RequestBody @Valid final CreateMemberRequest request) throws Exception {
        String code = memberService.initiateSignUp(request);  // 이 단계에서 이메일에 인증 코드가 전송됩니다.
        return ResponseEntity.ok().body("인증 코드가 전송됨 " + request.getEmail());
    }

    @PostMapping("/check-nickname")
    public ResponseEntity<Boolean> checkDuplicateNickName(@RequestBody CheckDuplicatedNickNameRequest checkDuplicatedNickNameRequest){
        return ResponseEntity.ok(memberService.isDuplicatedNickName(checkDuplicatedNickNameRequest.getNickname()));
    }

    @PostMapping("/check-phoneNumber")
    public ResponseEntity<Boolean> checkDuplicatePhoneNumber(@RequestBody CheckDuplicatedPhoneNumberRequest checkDuplicatedPhoneNumberRequest){
        return ResponseEntity.ok(memberService.isDuplicatedPhoneNumber(checkDuplicatedPhoneNumberRequest.getPhoneNumber()));
    }

    @PostMapping("/complete-sign-up")
    public ResponseEntity<CreateMemberResponse> completeSignUp(@RequestBody @Valid final MailConfirmDto request) {
        CreateMemberResponse response = memberService.verifyEmailAndCompleteSignUp(request.getEmail(), request.getCode());
        URI uri = URI.create("/find-my-guide/sign-up");
        return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/check-duplicate")
    public ResponseEntity<?> checkValidate(@RequestBody CheckDuplicatedEmailRequest userDuplicateCheckRequest) {
        boolean duplicated = memberService.CheckDuplicated(userDuplicateCheckRequest);
        if (duplicated) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }


    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@RequestBody @Valid final LoginMemberRequest loginMemberRequest) {
        HashMap<String, Object> token = memberService.login(loginMemberRequest);
        if (token != null)
            return ResponseEntity.ok().body(token);
        return ResponseEntity.badRequest().body("토큰이 null");
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(final Authentication authentication,
                                            @RequestBody MyPageChangePasswordRequest request) {
        try {
            memberService.changePassword((String)authentication.getPrincipal(), request);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<CreateMemberResponse> read(final Authentication authentication) {
        return ResponseEntity.ok(memberService.readMember((String) authentication.getPrincipal()));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, final Authentication authentication) {
        memberService.logout(request.getHeader(JwtTokenUtil.HEADER_STRING), (String) authentication.getPrincipal());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/find-email")
    public ResponseEntity<FindMemberResponse> findEmail(@RequestBody FindMemberRequest findMemberRequest) {
        return ResponseEntity.ok(memberService.findMemberEmail(findMemberRequest));

    }

    @PostMapping("/update")
    public ResponseEntity<UpdateMemberResponse> update(final Authentication authentication,
                                                       @RequestBody @Valid final UpdateMemberRequest request) {
        UpdateMemberResponse response = memberService.updateMember((String) authentication.getPrincipal(), request);

        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<DeleteMemberResponse> delete(final Authentication authentication) {

        DeleteMemberResponse response = memberService.deleteMember((String) authentication.getPrincipal());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-not-certified-email")
    public ResponseEntity<DeleteMemberResponse> deleteUndefinedEmailMember(@RequestParam String email) {

        DeleteMemberResponse response = memberService.deleteMember(email);

        return ResponseEntity.ok(response);
    }



    @PostMapping("/initiate-change-password")
    public ResponseEntity<String> initiatePasswordReset(@RequestBody SendEmailRequest email) {
        try {
            String responseMessage = memberService.initiatePasswordReset(email);
            return ResponseEntity.ok(responseMessage);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        try {
            memberService.resetPassword(resetPasswordRequest.getCode(), resetPasswordRequest.getNewPassword());
            return ResponseEntity.ok("패스워드가 성공적으로 변경됐습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




}

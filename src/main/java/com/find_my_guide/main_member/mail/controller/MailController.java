package com.find_my_guide.main_member.mail.controller;
import com.find_my_guide.main_member.mail.dto.MailConfirmDto;
import com.find_my_guide.main_member.mail.service.MailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/mail")
@RequiredArgsConstructor
@RestController
@Api(tags = "mail")
public class MailController {

    private final MailService mailService;

    @PostMapping("/send")
    @ApiOperation(value = "메일 인증 받는 API", notes = "지정한 메일로 인증 코드를 받기위한 API ", response = String.class)
    public ResponseEntity<String> sendMail(@RequestBody MailConfirmDto mailConfirmDto) throws Exception {
        String code = mailService.sendMail(mailConfirmDto.getEmail());
        return ResponseEntity.ok(code);
    }

    @PostMapping("/confirm")
    @ApiOperation(value = "인증 코드 확인 API", notes = "인증 코드를 확인하기 위한 API", response = Boolean.class)
    public ResponseEntity<Boolean> confirmMail(@RequestBody MailConfirmDto mailConfirmDto) throws Exception {
        return ResponseEntity.ok(mailService.confirmCode(mailConfirmDto));
    }
}

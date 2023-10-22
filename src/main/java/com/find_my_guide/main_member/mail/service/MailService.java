package com.find_my_guide.main_member.mail.service;


import com.find_my_guide.main_member.mail.dto.MailConfirmDto;
import com.find_my_guide.main_member.mail.model.Mail;
import com.find_my_guide.main_member.mail.repository.MailRepository;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MailService {

    private final JavaMailSender emailsender;

    private String ePw;

    private final MailRepository mailRepository;

    private final MemberRepository memberRepository;
    public MimeMailMessage createMessage(String sendMessage) throws Exception {
        MimeMessageHelper messageHelper = new MimeMessageHelper(emailsender.createMimeMessage(), true, "UTF-8");

        messageHelper.setTo(sendMessage);
        messageHelper.setSubject("Find My Guide 이메일 인증");

        String msgg = "";
        msgg += "<div style='margin:100px;'>";
        msgg += "<h1> 안녕하세요</h1>";
        msgg += "<h1> Find My Guide 입니다</h1>";
        msgg += "<br>";
        msgg += "<p>아래 코드를 이전 창으로 돌아가 입력해주세요<p>";
        msgg += "<br>";
        msgg += "<p>항상 당신의 여행를 응원합니다. 감사합니다!<p>";
        msgg += "<br>";
        msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg += "<h3'>인증 코드입니다.</h3>";
        msgg += "<div style='font-size:130%'>";
        msgg += "CODE : <strong>";
        msgg += ePw + "</strong><div><br/> "; // 메일에 인증번호 넣기
        msgg += "</div>";
        messageHelper.setText(msgg, true);

        messageHelper.setFrom("xodnjs8287@naver.com", "Find My Guide");
        return new MimeMailMessage(messageHelper.getMimeMessage());
    }

    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();
        while (true) {
            for (int i = 0; i < 8; i++) {
                int index = rnd.nextInt(3);
                switch (index) {
                    case 0:
                        key.append((char) ((int) (rnd.nextInt(26)) + 97));
                        // a~z (ex. 1+97=98 => (char)98 = 'b')
                        break;
                    case 1:
                        key.append((char) ((int) (rnd.nextInt(26)) + 65));
                        // A~Z
                        break;
                    case 2:
                        key.append((rnd.nextInt(10)));
                        // 0~9
                        break;
                }
            }
            return key.toString();
        }
    }

    public String sendSimpleMessage(String send) throws Exception {
        ePw = createKey();
        MimeMailMessage message = createMessage(send);

        try {
            emailsender.send(message.getMimeMessage());
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }

    @Transactional
    public String sendMail(String send) throws Exception {
        String code = sendSimpleMessage(send);
        Boolean confirm = mailRepository.existsMailByEmail(send);
        if (confirm) {
            mailRepository.delete(mailRepository.findMailByEmail(send));
        }
        Mail mail = Mail.builder()
                .code(code)
                .email(send)
                .build();
        mailRepository.save(mail);
        return code;
    }

    public Boolean confirmCode(MailConfirmDto mailConfirmDto) {
        Boolean confirm = mailRepository.existsMailByCodeAndEmail(
                mailConfirmDto.getCode(),
                mailConfirmDto.getEmail());
        if (confirm) {
            mailRepository.delete(
                    mailRepository.findMailByCodeAndEmail(
                            mailConfirmDto.getCode(),
                            mailConfirmDto.getEmail()));
        }
        return confirm;
    }

    public String sendPasswordResetMail(String recipientEmail) throws Exception {
        ePw = createKey();

        MimeMailMessage message = createPasswordResetMessage(recipientEmail);

        try {
            emailsender.send(message.getMimeMessage());
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }

        return ePw;
    }

    public MimeMailMessage createPasswordResetMessage(String recipientEmail) throws Exception {
        MimeMessageHelper messageHelper = new MimeMessageHelper(emailsender.createMimeMessage(), true, "UTF-8");

        messageHelper.setTo(recipientEmail);
        messageHelper.setSubject("Find My Guide 비밀번호 재설정");

        String msgg = "";
        msgg += "<div style='margin:100px;'>";
        msgg += "<h1> 안녕하세요</h1>";
        msgg += "<h1> Find My Guide 입니다</h1>";
        msgg += "<br>";
        msgg += "<p>아래의 링크를 클릭하여 비밀번호를 재설정해주세요.<p>";
        msgg += "<br>";
        msgg += "<a href='https://find-my-guide.site/login?token=" + ePw + "'>비밀번호 재설정하기</a>";
        msgg += "<br>";
        msgg += "<p>이 메일은 요청한 사용자에게만 발송됩니다.<p>";
        msgg += "</div>";

        messageHelper.setText(msgg, true);
        messageHelper.setFrom("xodnjs8287@naver.com", "Find My Guide");
        return new MimeMailMessage(messageHelper.getMimeMessage());
    }
}

package com.find_my_guide.main_member.member.service;

import com.find_my_guide.main_member.common.DuplicateException;
import com.find_my_guide.main_member.common.ErrorCode;
import com.find_my_guide.main_member.common.NotFoundException;
import com.find_my_guide.main_member.jwt.service.JwtTokenUtil;
import com.find_my_guide.main_member.jwt.service.RedisService;
import com.find_my_guide.main_member.mail.dto.MailConfirmDto;
import com.find_my_guide.main_member.mail.service.MailService;
import com.find_my_guide.main_member.member.domain.dto.*;
import com.find_my_guide.main_member.member.domain.entity.Gender;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.CustomMemberRepository;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import com.find_my_guide.main_member.temp_token.domain.PasswordResetToken;
import com.find_my_guide.main_member.temp_token.repository.PasswordResetTokenRepository;
import com.find_my_guide.main_tour_product.tour_history_manager.service.TourHistoryManagerService;
import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) //TODO 지우기
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    private final CustomMemberRepository customMemberRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenUtil jwtTokenUtil;

    private final PasswordResetTokenRepository passwordResetTokenRepository;

    private final RedisService redisService;

    private final TokenService tokenService;

    private final MailService mailService;

    private final TourHistoryManagerService tourHistoryManagerService;

    @Transactional
    public String initiateSignUp(CreateMemberRequest memberRequest) throws Exception {
        // 중복 확인
        isDuplicated(findByEmailHere(memberRequest.getEmail()), memberRequest.getEmail());
        isDuplicated(memberRepository.findByNickname(memberRequest.getNickname()), memberRequest.getNickname());
        isDuplicated(memberRepository.findByPhoneNumber(memberRequest.getPhoneNumber()), memberRequest.getPhoneNumber());

        // 이메일에 인증 코드 발송
        String code = mailService.sendMail(memberRequest.getEmail());

        // 사용자 정보를 '미인증' 상태로 저장
        Member tempMember = memberRequest.toMember(passwordEncoder);
        tempMember.setEmailVerified(Boolean.FALSE);
        memberRepository.save(tempMember);

        return code;
    }

    @Transactional
    public CreateMemberResponse verifyEmailAndCompleteSignUp(String email, String enteredCode) {
        Member tempMember = memberRepository.findByEmail(email).orElseThrow(() -> new NotFoundException());

        // DB에 저장된 인증 코드와 입력받은 인증 코드 확인
        if (!mailService.confirmCode(new MailConfirmDto(email, enteredCode))) {
            throw new IllegalArgumentException("정확하지 않은 코드입니다.");
        }

        // 사용자 상태를 '활성화'로 변경
        tempMember.setEmailVerified(Boolean.TRUE);

        return new CreateMemberResponse(tempMember);
    }


    public List<GuideResponse> findGuideByCriteria(Gender gender, int startAge, int endAge, Languages language, LocalDate date) {
        return customMemberRepository.findGuidesByCriteria(gender, startAge,endAge , language, date)
                .stream()
                .map(GuideResponse::new)
                .collect(Collectors.toList());
    }

    public Boolean CheckDuplicated(CheckDuplicatedEmailRequest userDuplicateCheckRequest) {
        return memberRepository.findByEmail(userDuplicateCheckRequest.getEmail()).isPresent();
    }

    public void logout(final String token, final String userId) {
        redisService.deleteToken(userId);
        redisService.storeAccessToken(token);
    }


    public CreateMemberResponse readMember(String email) {
        Optional<Member> member = findByEmailHere(email);

        isExistedEmail(member, email);

        return new CreateMemberResponse(member.get());
    }

    @Transactional
    public UpdateMemberResponse updateMember(String email, UpdateMemberRequest memberRequest) {
        Optional<Member> member = findByEmailHere(email);

        isExistedEmail(member, email);

        member.get()
                .update(
                        memberRequest.getNickname(),
                        memberRequest.getPhoneNumber(),
                        memberRequest.getNationalCertificationOfGuideYn(),
                        memberRequest.getGuideExperience(),
                        memberRequest.getProfilePicture(),
                        memberRequest.getLanguages(),
                        memberRequest.getGuideIntro()
                );

        return new UpdateMemberResponse(member.get());
    }

    @Transactional
    public DeleteMemberResponse deleteMember(String email) {
        Optional<Member> member = findByEmailHere(email);

        isExistedEmail(member, email);

        if (tourHistoryManagerService.hasReservationHistory(member.get().getIdx())) {
            throw new IllegalArgumentException("예약 내역이 존재 하여 삭제할 수 없습니다.");
        }


        memberRepository.delete(member.get());


        return new DeleteMemberResponse(member.get());
    }

    public HashMap<String, Object> login(final LoginMemberRequest loginMemberRequest) {

        String userId = loginMemberRequest.getEmail();
        String password = loginMemberRequest.getPassword();
        Member member = findByEmail(userId);

        if(!member.isEmailVerified()){
            throw new NotFoundException("이메일 인증이 완료되지 않았습니다. ");

        }
        if (passwordEncoder.matches(password, member.getPassword())) {
            HashMap<String, Object> stringObjectHashMap = tokenService.generateTokens(userId);
            return stringObjectHashMap;
        }
        return null;
    }

    @Transactional
    public void changePassword(String email, MyPageChangePasswordRequest request){

        Member member = findByEmail(email);

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new RuntimeException("현재 비밀번호가 올바르지 않습니다.");
        }

        if (!request.getNewPassword().equals(request.getNewPasswordAgain())) {
            throw new RuntimeException("새로운 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        }

        member.setPassword(passwordEncoder.encode(request.getNewPassword()));
    }
    public FindMemberResponse findMemberEmail(FindMemberRequest findMemberRequest) {
        return new FindMemberResponse(checkValidMember(findMemberRequest));

    }


    @Transactional
    public String initiatePasswordReset(SendEmailRequest email) throws Exception {
        Member member = memberRepository.findByEmail(email.getEmail())
                .orElseThrow(() -> new NotFoundException("No member associated with the provided email"));

        // 인증 코드 생성 및 이메일 전송
        String code = mailService.sendPasswordResetMail(email.getEmail());

        // 데이터베이스에 인증 코드 저장
        PasswordResetToken passwordResetToken = new PasswordResetToken();

        passwordResetToken.setToken(email.getEmail(), code, LocalDateTime.now().plusHours(1));

        passwordResetTokenRepository.save(passwordResetToken);

        return "패스워드 변경 링크가 이메일로 발송되었습니다. ";
    }

    @Transactional
    public void resetPassword(String code, String newPassword) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(code)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 코드이거나 코드가 만료 되었습니다."));

        if (passwordResetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("만료된 코드");
        }

        Member member = memberRepository.findByEmail(passwordResetToken.getEmail())
                .orElseThrow(() -> new NotFoundException("등록된 회원이 아닙니다."));

        // 비밀번호 업데이트
        member.setPassword(passwordEncoder.encode(newPassword));

        // 인증 코드 삭제
        passwordResetTokenRepository.delete(passwordResetToken);
    }

    public GuideResponse registerGuideCertification(String email, GuideCertificationRegisterRequest guideCertificationRegisterRequest){
        Member member = findByEmail(email);

        member.addGuideCertification(guideCertificationRegisterRequest.getGuideCertification(),guideCertificationRegisterRequest.getLanguages());

        Member save = memberRepository.save(member);

        return new GuideResponse(save);
    }

    public List<GuideResponse> getTop10PopularGuides() {
        return customMemberRepository.findPopularGuidesWithTies()
                .stream()
                .map(GuideResponse::new)
                .collect(Collectors.toList());
    }

    public List<GuideResponse> findAllGuides() {
        return memberRepository.findAllGuides()
                .stream()
                .map(GuideResponse::new)
                .collect(Collectors.toList());
    }

    public GuideResponse guideDetail(Long guideId){
        return memberRepository.findAllGuides()
                .stream().filter(it -> it.getIdx()==guideId)
                .map(GuideResponse::new)
                .findAny().orElseThrow(() -> new NotFoundException("이 가이드는 존재 하지 않습니다."));

    }

    public Boolean isDuplicatedNickName(String nickName) {
        return !memberRepository.findByNickname(nickName).isPresent();
    }

    public Boolean isDuplicatedPhoneNumber(String phoneNumber) {
        return !memberRepository.findByPhoneNumber(phoneNumber).isPresent();
    }



    private Member checkValidMember(FindMemberRequest findMemberRequest) {
        memberRepository.findByName(findMemberRequest.getName()).orElseThrow(() ->
                new NotFoundException("등록된 회원이 아닙니다")
        );

        Member member = memberRepository.findByPhoneNumber(findMemberRequest.getPhoneNumber()).orElseThrow(()
                -> new NotFoundException("등록된 회원이 아닙니다")
        );

        return member;
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException());
    }


    private boolean isRightPassword(LoginMemberRequest loginRequest, Member member) {
        if (!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
            log.error("Invalid password for email: " + loginRequest.getEmail());
            throw new IllegalArgumentException("Invalid password");
        }
        return true;
    }

    private void isExistedEmail(Optional<Member> memberOpt, String loginRequest) {
        if (memberOpt.isEmpty()) {
            log.error(loginRequest + "is not found");
            throw new NotFoundException(loginRequest);
        }
    }

    private void isDuplicated(Optional<Member> memberRequest, String memberRequest1) {
        if (memberRequest.isPresent()) {
            log.error(memberRequest1 + " is duplicated");
            throw new DuplicateException(memberRequest1, ErrorCode.DUPLICATION);
        }
    }


    private Optional<Member> findByEmailHere(String email) {
        return memberRepository.findByEmail(email);
    }
}

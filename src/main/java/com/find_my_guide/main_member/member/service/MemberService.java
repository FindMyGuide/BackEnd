package com.find_my_guide.main_member.member.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
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
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_history_manager.service.TourHistoryManagerService;
import com.find_my_guide.main_tour_product.tour_product.domain.Languages;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
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

    private final AmazonS3 amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

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


    public List<GuideSearchResponse> findGuideByCriteria(Gender gender, int startAge, int endAge, Languages language, LocalDate date) {

        return customMemberRepository.findGuidesByCriteria(gender, startAge, endAge, language, date)
                .stream()
                .map(GuideSearchResponse::new)
                .peek(guideResponse -> {
                    Member member = memberRepository.findById(guideResponse.getGuideId())
                            .orElseThrow(() -> new NotFoundException("이 가이드는 존재 하지 않습니다."));

                    List<TourProductInformationResponse> tourProductResponses = new ArrayList<>();

                    if (member.getTourHistoriesAsGuide() != null) {
                        tourProductResponses = member.getTourHistoriesAsGuide()
                                .stream()
                                .map(TourHistoryManager::getTourProduct)
                                .filter(Objects::nonNull)
                                .map(TourProductInformationResponse::new)
                                .collect(Collectors.toList());
                    }

                    guideResponse.setTourProductInformation(tourProductResponses);
                })
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

        if (!member.isEmailVerified()) {
            throw new NotFoundException("이메일 인증이 완료되지 않았습니다. ");

        }
        if (passwordEncoder.matches(password, member.getPassword())) {
            HashMap<String, Object> stringObjectHashMap = tokenService.generateTokens(userId);
            return stringObjectHashMap;
        }
        return null;
    }

    @Transactional
    public void changePassword(String email, MyPageChangePasswordRequest request) {

        Member member = findByEmail(email);

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new RuntimeException("현재 비밀번호가 올바르지 않습니다.");
        }

        if (!request.getNewPassword().equals(request.getNewPasswordAgain())) {
            throw new RuntimeException("새로운 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
        }

        member.setPassword(passwordEncoder.encode(request.getNewPassword()));
    }

    @Transactional
    public void changeProfile(String email, MultipartFile file) {
        try {
            Member member = memberRepository.findByEmail(email).orElseThrow();
            if (member.getProfilePicture() != null && !member.getProfilePicture().replace("https://findmyguide.s3.amazonaws.com/", "").equals("noProfile.svg")){
                deleteFile(URLDecoder.decode(member.getProfilePicture().replace("https://findmyguide.s3.amazonaws.com/", ""), StandardCharsets.UTF_8));
            }
            String fileName = UUID.randomUUID() + file.getOriginalFilename();

            String fileUrl = "https://" + bucket + ".s3.amazonaws.com/" + fileName;
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
            member.setProfile(fileUrl);
            memberRepository.save(member);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(String fileName) throws IOException {
        try {
            amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, fileName));
        } catch (SdkClientException e) {
            throw new IOException("Error deleteing file from S3", e);
        }
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

    public GuideResponse registerGuideCertification(String email, GuideCertificationRegisterRequest guideCertificationRegisterRequest) {
        Member member = findByEmail(email);

        member.addGuideCertification(guideCertificationRegisterRequest.getGuideCertification(), guideCertificationRegisterRequest.getLanguages());

        Member save = memberRepository.save(member);

        return new GuideResponse(save);
    }

    public List<GuideResponse> getTop10PopularGuides() {

        List<GuideResponse> guideResponses = customMemberRepository.findPopularGuidesWithTies()
                .stream()
                .map(GuideResponse::new)
                .collect(Collectors.toList());

        for (GuideResponse guideResponse : guideResponses) {
            Member guide = memberRepository.findById(guideResponse.getGuideId())
                    .orElseThrow(() -> new NotFoundException("가이드를 찾을 수 없습니다."));
            List<TourProductResponse> tourProductResponses = guide.getTourHistoriesAsGuide().stream()
                    .map(TourHistoryManager::getTourProduct)
                    .map(TourProductResponse::new)
                    .collect(Collectors.toList());
            guideResponse.setTourProductResponses(tourProductResponses);
        }
        return guideResponses;
    }

    public List<GuideResponse> findAllGuides() {
        return memberRepository.findAllGuides()
                .stream()
                .map(GuideResponse::new)
                .collect(Collectors.toList());
    }

    public GuideDetailResponse guideDetail(Long guideId) {
        Member member = memberRepository.findById(guideId)
                .orElseThrow(() -> new NotFoundException("이 가이드는 존재 하지 않습니다."));

        List<TourProductResponse> tourProductResponses = member.getTourHistoriesAsGuide()
                .stream()
                .map(TourHistoryManager::getTourProduct)
                .map(TourProductResponse::new)  // 각 TourProduct 객체를 TourProductResponse로 변환
                .collect(Collectors.toList());

        return new GuideDetailResponse(member, tourProductResponses);
    }


    public Boolean isDuplicatedNickName(String nickName) {
        return !memberRepository.findByNickname(nickName).isPresent();
    }

    public Boolean isDuplicatedPhoneNumber(String phoneNumber) {
        return !memberRepository.findByPhoneNumber(phoneNumber).isPresent();
    }


    private Member checkValidMember(FindMemberRequest findMemberRequest) {

        memberRepository.findByPhoneNumber(findMemberRequest.getPhoneNumber()).orElseThrow(()
                -> new NotFoundException("등록된 회원이 아닙니다")
        );

        Member member = memberRepository.findByNameAndPhoneNumber(findMemberRequest.getName(), findMemberRequest.getPhoneNumber())
                .orElseThrow();

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

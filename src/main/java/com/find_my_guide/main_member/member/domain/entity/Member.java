package com.find_my_guide.main_member.member.domain.entity;

import com.find_my_guide.main_member.guideLike.domain.GuideLike;
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_product_like.domain.TourProductLike;
import com.find_my_guide.main_tour_product.tour_product_review.domain.TourProductReview;
import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@DynamicInsert
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column
    private String nationality;     //Enum으로 관리할 것인가? => 국가가 약 200여개? 정도라 작성하는데 오래걸림
    //API로 할것인가? 국가 테이블을 따로 생성해서 가져다 쓸것인가?
    // api or table 찬성, 일단 공공 데이터 포털에 있는 국가 코드 api 예제 코드는 openapi.publica 아래 작성해두었음.
    @Enumerated(EnumType.STRING) //EnumType.ORDINAL : enum의 순서 값, EnumType.STRING : enum의 이름
    @Column
    private Gender gender;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "national_certification_of_guide_yn", nullable = false)
    private Boolean nationalCertificationOfGuideYn;

    @Column
    private String refreshToken;

    @Column(name = "kakao_id")
    private String kakaoId;

    @Column
    private boolean isEmailVerified;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<WantTourProduct> wantTourProducts = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<TourProductReview> tourProductReviews = new ArrayList<>();

    @OneToMany(mappedBy = "tourist")
    private List<TourHistoryManager> tourHistoriesAsTourist = new ArrayList<>();

    @OneToMany(mappedBy = "guide")
    private List<GuideLike> likedByMembers = new ArrayList<>();

    @OneToMany(mappedBy = "guide")
    private List<TourHistoryManager> tourHistoriesAsGuide = new ArrayList<>();


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<TourProductLike> tourProductLikes = new ArrayList<>();

    public List<TourHistoryManager> getTourHistoriesAsGuide() {
        return tourHistoriesAsGuide;
    }

    public List<TourHistoryManager> getTourHistoriesAsTourist() {
        return tourHistoriesAsTourist;
    }

    public void update(String nickname, String phoneNumber, String nationalCertificationOfGuideYn) {

        if (!Objects.isNull(nickname)) {
            this.nickname = nickname;
        }

        if (!Objects.isNull(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        }

        if (!Objects.isNull(nationalCertificationOfGuideYn)) {
            this.nationalCertificationOfGuideYn = nationalCertificationOfGuideYn.equalsIgnoreCase("Y");
        }
    }

    public void setEmailVerified(Boolean trueOrFalse){
        this.isEmailVerified = trueOrFalse;
    }

    public void setKakaoId(String kakaoId) {
        this.kakaoId = kakaoId;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}

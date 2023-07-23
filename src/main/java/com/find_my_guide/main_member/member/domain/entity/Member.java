package com.find_my_guide.main_member.member.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_product_like.domain.TourProductLike;
import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "member")
    private List<WantTourProduct> wantTourProducts = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<TourHistoryManager> tourHistoryManagers = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<TourProductLike> tourProductLikes = new ArrayList<>();
    public void update(PasswordEncoder passwordEncoder, String password, String nickname, String phoneNumber, String nationalCertificationOfGuideYn) {

        if(!Objects.isNull(password)) {
            this.password = passwordEncoder.encode(password);
        }

        if(!Objects.isNull(nickname)) {
            this.nickname = nickname;
        }

        if(!Objects.isNull(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        }

        if(!Objects.isNull(nationalCertificationOfGuideYn)) {
            this.nationalCertificationOfGuideYn = nationalCertificationOfGuideYn.equalsIgnoreCase("Y");
        }
    }
}

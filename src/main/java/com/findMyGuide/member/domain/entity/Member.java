package com.findMyGuide.member.domain.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "MEM_SEQ")
    private Long idx;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String nationality;     //Enum으로 관리할 것인가? => 국가가 약 200여개? 정도라 작성하는데 오래걸림
                                    //API로 할것인가? 국가 테이블을 따로 생성해서 가져다 쓸것인가?
                                    // api or table 찬성, 일단 공공 데이터 포털에 있는 국가 코드 api 예제 코드는 openapi.publica 아래 작성해두었음.
    @Enumerated(EnumType.STRING) //EnumType.ORDINAL : enum의 순서 값, EnumType.STRING : enum의 이름
    @Column(nullable = false)
    private Gender gender;

    @Column(nullable = false)
    private Integer age;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "national_certification_of_guide_yn", nullable = false)
    private Boolean nationalCertificationOfGuideYn;
}

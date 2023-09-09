package com.find_my_guide.main_member.guideLike.domain;

import com.find_my_guide.main_member.member.domain.entity.Member;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuideLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;  // 좋아요를 누른 멤버

    @ManyToOne
    @JoinColumn(name = "guide_id")
    private Member guide;  // 좋아요를 받은 가이드

}

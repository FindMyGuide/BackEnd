package com.find_my_guide.main_tour_product.location_like.domain;


import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.location.domain.Location;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationLike {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}

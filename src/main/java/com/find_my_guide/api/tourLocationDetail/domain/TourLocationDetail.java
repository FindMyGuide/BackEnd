package com.find_my_guide.api.tourLocationDetail.domain;

import com.find_my_guide.api.festival.domain.Festival;
import com.find_my_guide.api.tourLocation.domain.TourLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourLocationDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "tourLocation_id")
    private TourLocation tourLocation;

    private String place;
    private String playtime;
    private String startDate;
    private String expense;
    private String content;


}

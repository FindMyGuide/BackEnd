package com.find_my_guide.api.tourLocationDetail.domain;

import com.find_my_guide.api.festival.domain.Festival;
import com.find_my_guide.api.tourLocation.domain.TourLocation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourLocationDetail {

    @Id
    @OneToOne(mappedBy = "festival")
    @JoinColumn(name = "qna_id")
    private TourLocation id;

    private String place;
    private String playtime;
    private String startDate;
    private String expense;
    private String content;

}

package com.findMyGuide.api.tourLocationDetail.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourLocationDetail {

    @Id
    private Long id;
    private String place;
    private String playtime;
    private String startDate;
    private String expense;
    private String content;

}

package com.findMyGuide.api.festivalDetail.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FestivalDetail {

    @Id
    private Long id;
    private String place;
    private String playtime;
    private String startDate;
    private String expense;
    private String content;

}

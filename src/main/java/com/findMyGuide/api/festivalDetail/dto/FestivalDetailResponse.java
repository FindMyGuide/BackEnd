package com.findMyGuide.api.festivalDetail.dto;

import com.findMyGuide.api.festivalDetail.domain.FestivalDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FestivalDetailResponse {

    private Long id;
    private String place;
    private String playtime;
    private String startDate;
    private String expense;
    private String content;

    public FestivalDetailResponse(FestivalDetail festivalDetail) {
        this.id = festivalDetail.getId();
        this.place = festivalDetail.getPlace();
        this.playtime = festivalDetail.getPlaytime();
        this.startDate = festivalDetail.getStartDate();
        this.expense = festivalDetail.getExpense();
        this.content = festivalDetail.getContent();

    }
}

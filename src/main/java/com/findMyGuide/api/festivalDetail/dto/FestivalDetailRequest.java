package com.findMyGuide.api.festivalDetail.dto;

import com.findMyGuide.api.festivalDetail.domain.FestivalDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FestivalDetailRequest {

    private Long id;
    private String place;
    private String playtime;
    private String startDate;
    private String expense;
    private String content;

    public FestivalDetail toFestivalDetail(Long id, String place, String playtime,
                                           String startDate, String expense, String content) {
        this.id = id;
        this.place = place;
        this.playtime = playtime;
        this.startDate = startDate;
        this.expense = expense;
        this.content = content;

        return FestivalDetail.builder()
                .id(this.id)
                .place(this.place)
                .playtime(this.playtime)
                .startDate(this.startDate)
                .expense(this.expense)
                .content(this.content).build();
    }
}

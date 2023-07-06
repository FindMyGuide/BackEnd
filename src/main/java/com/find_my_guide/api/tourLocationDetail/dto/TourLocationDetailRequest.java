package com.find_my_guide.api.tourLocationDetail.dto;

import com.find_my_guide.api.tourLocationDetail.domain.TourLocationDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TourLocationDetailRequest {

    private Long id;
    private String place;
    private String playtime;
    private String startDate;
    private String expense;
    private String content;

    public TourLocationDetail toFestivalDetail(Long id, String place, String playtime,
                                               String startDate, String expense, String content) {
        this.id = id;
        this.place = place;
        this.playtime = playtime;
        this.startDate = startDate;
        this.expense = expense;
        this.content = content;

        return TourLocationDetail.builder()
                .place(this.place)
                .playtime(this.playtime)
                .startDate(this.startDate)
                .expense(this.expense)
                .content(this.content).build();
    }
}

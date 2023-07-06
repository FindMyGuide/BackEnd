package com.find_my_guide.api.tourLocationDetail.dto;

import com.find_my_guide.api.tourLocationDetail.domain.TourLocationDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TourLocationDetailResponse {

    private Long id;
    private String place;
    private String playtime;
    private String startDate;
    private String expense;
    private String content;

    public TourLocationDetailResponse(TourLocationDetail tourLocationDetail) {
        this.place = tourLocationDetail.getPlace();
        this.playtime = tourLocationDetail.getPlaytime();
        this.startDate = tourLocationDetail.getStartDate();
        this.expense = tourLocationDetail.getExpense();
        this.content = tourLocationDetail.getContent();
    }
}

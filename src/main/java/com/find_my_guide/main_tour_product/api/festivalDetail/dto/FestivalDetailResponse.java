package com.find_my_guide.main_tour_product.api.festivalDetail.dto;

import com.find_my_guide.main_tour_product.api.festivalDetail.domain.FestivalDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String mapX;
    private String mapY;

    public FestivalDetailResponse(FestivalDetail festivalDetail) {
        this.id = festivalDetail.getFestivalDetailId();
        this.place = festivalDetail.getPlace();
        this.playtime = festivalDetail.getPlaytime();
        this.startDate = festivalDetail.getStartDate();
        this.expense = festivalDetail.getExpense();
        this.content = festivalDetail.getContent();
        this.mapX = festivalDetail.getFestival().getMapx();
        this.mapY = festivalDetail.getFestival().getMapy();
    }
}

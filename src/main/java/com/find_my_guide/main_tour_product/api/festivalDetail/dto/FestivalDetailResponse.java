package com.find_my_guide.main_tour_product.api.festivalDetail.dto;

import com.find_my_guide.main_tour_product.api.festival.domain.Festival;
import com.find_my_guide.main_tour_product.api.festivalDetail.domain.FestivalDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FestivalDetailResponse {

    private String place;
    private String playtime;
    private String startDate;
    private String endDate;
    private String expense;
    private String content;
    private String mapX;
    private String mapY;
    private String image;
    private String title;

    public FestivalDetailResponse(FestivalDetail festivalDetail) {
        this.place = festivalDetail.getPlace();
        this.playtime = festivalDetail.getPlaytime();
        this.startDate = festivalDetail.getStartDate();
        this.expense = festivalDetail.getExpense();
        this.content = festivalDetail.getContent();
        this.mapX = festivalDetail.getFestival().getMapx();
        this.mapY = festivalDetail.getFestival().getMapy();
        this.image = festivalDetail.getFestival().getImage();
        this.title = festivalDetail.getFestival().getTitle();
    }

    public FestivalDetailResponse(Festival festival, String place, String playtime, String startDate, String endDate,
                                  String expense, String content) {
        this.place = place;
        this.playtime = playtime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expense = expense;
        this.content = content;
        this.mapX = festival.getMapx();
        this.mapY = festival.getMapy();
        this.image = festival.getImage();
        this.title = festival.getTitle();
    }
}

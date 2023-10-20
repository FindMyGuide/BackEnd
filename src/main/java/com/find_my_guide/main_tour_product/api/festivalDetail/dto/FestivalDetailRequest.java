package com.find_my_guide.main_tour_product.api.festivalDetail.dto;

import com.find_my_guide.main_tour_product.api.festival.domain.Festival;
import com.find_my_guide.main_tour_product.api.festivalDetail.domain.FestivalDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FestivalDetailRequest {

    private Long id;

    private String place;
    private String playtime;
    private String startDate;
    private String endDate;
    private String expense;
    private String content;


    public FestivalDetail toFestivalDetail() {
        return FestivalDetail.builder()
                .festivalDetailId(this.id)
                .place(this.place)
                .playtime(this.playtime)
                .startDate(this.startDate)
                .endDate(this.endDate).build();
    }

    public FestivalDetailRequest( String place, String playtime,
                                 String startDate, String endDate, String expense, String content) {
        this.place = place;
        this.playtime = playtime;
        this.startDate = startDate;
        this.expense = expense;
        this.content = content;
        this.endDate = endDate;
    }
}

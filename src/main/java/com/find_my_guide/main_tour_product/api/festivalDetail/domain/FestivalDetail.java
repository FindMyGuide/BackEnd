package com.find_my_guide.main_tour_product.api.festivalDetail.domain;

import com.find_my_guide.main_tour_product.api.festival.domain.Festival;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FestivalDetail {
    @Id
    private Long festivalDetailId;

    @OneToOne
    @JoinColumn(name = "festival_id")
    private Festival festival;

    private String place;
    private String playtime;
    private String startDate;
    private String endDate;
    private String expense;
    private String content;


    public void setFestival(Festival festival) {
        this.festival = festival;
    }
}

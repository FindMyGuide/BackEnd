package com.find_my_guide.main_tour_product.api.festival.dto;

import com.find_my_guide.main_tour_product.api.festival.domain.Festival;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FestivalResponse {

    private Long id;
    private String title;
    private String image;
    private Boolean progress;

    public FestivalResponse(Festival festival, Boolean progress) {
        this.id = festival.getId();
        this.title = festival.getTitle();
        this.image = festival.getImage();
        this.progress = progress;
    }

}
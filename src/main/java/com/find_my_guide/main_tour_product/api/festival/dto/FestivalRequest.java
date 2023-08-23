package com.find_my_guide.main_tour_product.api.festival.dto;

import com.find_my_guide.main_tour_product.api.festival.domain.Festival;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FestivalRequest {

    private Long id;
    private String title;
    private String image;
    private String mapx;
    private String mapy;

    public Festival toFestival() {

        return Festival.builder()
                .id(this.id)
                .title(this.title)
                .image(this.image)
                .mapx(this.mapx)
                .mapy(this.mapy)
                .build();
    }
}

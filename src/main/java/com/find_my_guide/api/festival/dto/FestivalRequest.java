package com.find_my_guide.api.festival.dto;

import com.find_my_guide.api.festival.domain.Festival;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FestivalRequest {

    private Long id;
    private String title;
    private String image;
    private String mapX;
    private String mapY;

    public Festival toFestival(Long id, String title, String image,
                               String mapx, String mapy) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.mapX = mapx;
        this.mapY = mapy;

        return Festival.builder()
                .id(this.id)
                .title(this.title)
                .image(this.image)
                .mapX(this.mapX)
                .mapY(this.mapY)
                .build();
    }
}

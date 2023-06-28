package com.findMyGuide.api.festival.dto;

import com.findMyGuide.api.festival.domain.Festival;
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
    private String mapx;
    private String mapy;

    public Festival toFestival(Long id, String title, String image,
                               String mapx, String mapy) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.mapx = mapx;
        this.mapy = mapy;

        return Festival.builder()
                .id(this.id)
                .title(this.title)
                .image(this.image)
                .mapx(this.mapx)
                .mapy(this.mapy)
                .build();
    }
}

package com.find_my_guide.api.festival.domain;

import com.find_my_guide.api.festivalDetail.domain.FestivalDetail;
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
public class Festival {

    @Id
    @Column(name = "festival_id")
    private Long id;

    private String title;
    private String image;
    private String mapx;
    private String mapy;

    @OneToOne(mappedBy = "festival", cascade = CascadeType.ALL)
    private FestivalDetail festivalDetail;
}

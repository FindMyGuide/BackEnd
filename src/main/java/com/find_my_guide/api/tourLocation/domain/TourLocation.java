package com.find_my_guide.api.tourLocation.domain;

import com.find_my_guide.api.tourLocationDetail.domain.TourLocationDetail;
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
public class TourLocation {

    @Id
    private Long id;

    private String title;
    private String image;
    private String mapx;
    private String mapy;

    @OneToOne(mappedBy = "tourLocation", cascade = CascadeType.ALL)
    private TourLocationDetail tourLocationDetail;

}

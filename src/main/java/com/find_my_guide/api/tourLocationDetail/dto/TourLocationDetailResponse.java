package com.find_my_guide.api.tourLocationDetail.dto;

import com.find_my_guide.api.tourLocationDetail.domain.TourLocationDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TourLocationDetailResponse {

    private Long id;
    private String infoCenter;
    private String restDate;
    private String useTime;
    private String parking;
    private String infoText;

    public TourLocationDetailResponse(TourLocationDetail tourLocationDetail) {
        this.infoCenter = tourLocationDetail.getInfoCenter();
        this.restDate = tourLocationDetail.getRestDate();
        this.useTime = tourLocationDetail.getUseTime();
        this.parking = tourLocationDetail.getParking();
        this.infoText = tourLocationDetail.getInfoText();
    }
}

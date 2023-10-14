package com.find_my_guide.main_tour_product.api.tourLocationDetail.dto;

import com.find_my_guide.main_tour_product.api.tourLocation.domain.TourLocation;
import com.find_my_guide.main_tour_product.api.tourLocationDetail.domain.TourLocationDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TourLocationDetailResponse {

    private String infoCenter;
    private String restDate;
    private String useTime;
    private String parking;
    private String infoText;
    private String title;
    private String image;
    private String mapX;
    private String mapY;

    public TourLocationDetailResponse(TourLocationDetail tourLocationDetail) {
        this.infoCenter = tourLocationDetail.getInfoCenter();
        this.restDate = tourLocationDetail.getRestDate();
        this.useTime = tourLocationDetail.getUseTime();
        this.parking = tourLocationDetail.getParking();
        this.infoText = tourLocationDetail.getInfoText();
    }

    public TourLocationDetailResponse(TourLocation tourLocation, String infoCenter, String restDate, String useTime, String parking,
                                      String infoText) {
        this.infoCenter = infoCenter;
        this.restDate = restDate;
        this.useTime = useTime;
        this.parking = parking;
        this.infoText = infoText;
        this.title = tourLocation.getTitle();
        this.image = tourLocation.getImage();
        this.mapX = tourLocation.getMapx();
        this.mapY = tourLocation.getMapy();
    }
}

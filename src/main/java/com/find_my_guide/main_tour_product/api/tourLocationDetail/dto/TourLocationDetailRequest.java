package com.find_my_guide.main_tour_product.api.tourLocationDetail.dto;

import com.find_my_guide.main_tour_product.api.tourLocation.domain.TourLocation;
import com.find_my_guide.main_tour_product.api.tourLocationDetail.domain.TourLocationDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TourLocationDetailRequest {

    private Long id;
    private TourLocation tourLocation;
    private String infoCenter;
    private String restDate;
    private String useTime;
    private String parking;
    private String infoText;

    public TourLocationDetail toTourLocationDetail() {


        return TourLocationDetail.builder()
                .id(this.id)
                .tourLocation(this.tourLocation)
                .infoCenter(this.infoCenter)
                .restDate(this.restDate)
                .useTime(this.useTime)
                .parking(this.parking)
                .infoText(this.infoText)
                .build();
    }

    public TourLocationDetailRequest(String infoCenter, String restDate,
                                     String useTime, String parking, String infoText) {
        this.infoCenter = infoCenter;
        this.restDate = restDate;
        this.useTime = useTime;
        this.parking = parking;
        this.infoText = infoText;
    }
}

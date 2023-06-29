package com.find_my_guide.api.restaurant.dto;

import com.find_my_guide.api.restaurant.domain.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RestaurantRequest {
    private Long id;

    private String title;
    private String address;
    private String mapx;
    private String mapy;
    private String telNo;
    private String restaurantCode;
    private String restaurantLcnc;
    private String introduce;

    public Restaurant toRestaurant(Long id, String title, String address, String mapx, String mapy,
                                   String telNo, String restaurantCode, String restaurantLcnc,
                                   String introduce) {

        this.id = id;
        this.title = title;
        this.address = address;
        this.mapx = mapx;
        this.mapy = mapy;
        this.telNo = telNo;
        this.restaurantCode = restaurantCode;
        this.restaurantLcnc = restaurantLcnc;
        this.introduce = introduce;

        return Restaurant.builder()
                .id(this.id)
                .title(this.title)
                .address(this.address)
                .mapx(this.mapx)
                .mapy(this.mapy)
                .telNo(this.telNo)
                .restaurantCode(this.restaurantCode)
                .restaurantLcnc(this.restaurantLcnc)
                .introduce(this.introduce)
                .build();
    }
}

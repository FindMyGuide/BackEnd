package com.find_my_guide.main_tour_product.api.restaurant.dto;

import com.find_my_guide.main_tour_product.api.restaurant.domain.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RestaurantResponse {

    private Long id;

    private String title;
    private String address;
    private String mapx;
    private String mapy;
    private String telNo;
    private String restaurantCode;
    private String restaurantLcnc;
    private String introduce;

    public RestaurantResponse(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.title = restaurant.getTitle();
        this.address = restaurant.getAddress();
        this.mapx = restaurant.getMapx();
        this.mapy = restaurant.getMapy();
        this.telNo = restaurant.getTelNo();
        this.restaurantCode = restaurant.getRestaurantCode();
        this.restaurantLcnc = restaurant.getRestaurantLcnc();
        this.introduce = restaurant.getIntroduce();
    }
}

package com.find_my_guide.main_tour_product.api.restaurant.dto;

import com.find_my_guide.main_tour_product.api.restaurant.domain.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ManyRestaurantResponse {

    private Long id;
    private String title;
    private String address;
    private String restaurantCode;
    private String restaurantLcnc;

    public ManyRestaurantResponse(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.title = restaurant.getTitle();
        this.address = restaurant.getAddress();
        this.restaurantCode = restaurant.getRestaurantCode();
        this.restaurantLcnc = restaurant.getRestaurantLcnc();
    }
}

package com.find_my_guide.main_tour_product.api.restaurantImage.dto;

import com.find_my_guide.main_tour_product.api.restaurant.domain.Restaurant;
import com.find_my_guide.main_tour_product.api.restaurantImage.domain.RestaurantImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantImageRequest {

    private Long id;
    private String imageUrl;
    private Restaurant restaurant;

    public RestaurantImage toRestaurantImage() {
        return  RestaurantImage.builder()
                .id(id)
                .imageUrl(imageUrl)
                .restaurant(restaurant)
                .build();
    }
}

package com.find_my_guide.main_tour_product.api.restaurant.domain;

import com.find_my_guide.main_tour_product.api.restaurantImage.domain.RestaurantImage;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    private Long id;

    private String title;
    private String address;
    private String mapx;
    private String mapy;
    private String telNo;
    private String restaurantCode;
    private String restaurantLcnc;
    @Column(length = 5000)
    private String introduce;

    @OneToMany(mappedBy = "restaurant")
    private List<RestaurantImage> restaurantImages;



}

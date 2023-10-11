package com.find_my_guide.main_tour_product.want_tour_product.dto;

import com.find_my_guide.main_tour_product.want_tour_product.domain.Vehicle;
import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import com.find_my_guide.main_tour_product.want_tour_product_location.dto.WantTourProductLocationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.util.annotation.Nullable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WantTourProductResponse {

    private Long id;
    private LocalDateTime createAt;
    private String title;
    private String content;
    private BigDecimal price;

    @Nullable
    private int totalPeople;

    @Nullable
    private Vehicle vehicle;

    @Nullable
    private List<WantTourProductLocationResponse> locationResponses ;

    public WantTourProductResponse(WantTourProduct wantTourProduct) {
        this.id = wantTourProduct.getWantTourProductId();
        this.createAt = wantTourProduct.getCreatedAt();
        this.price = wantTourProduct.getPrice().getPrice();
        this.title = wantTourProduct.getTitle().getTitle();
        if (wantTourProduct.getTotalPeople() != null) {
            this.totalPeople = wantTourProduct.getTotalPeople();
        }
        this.content = wantTourProduct.getContent().getContent();
        this.vehicle = wantTourProduct.getVehicle();
        this.locationResponses = wantTourProduct.getWantTourProductLocations().stream()
                .map(WantTourProductLocationResponse::new)
                .collect(Collectors.toList());
    }
}

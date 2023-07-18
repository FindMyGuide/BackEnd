package com.find_my_guide.main_tour_product.want_tour_product.dto;

import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import com.find_my_guide.main_tour_product.want_tour_product_location.dto.WantTourProductLocationResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WantTourProductResponse {

    private Long id;
    private LocalDate createAt;
    private String title;
    private String content;
    private List<WantTourProductLocationResponse> locationResponses;

    public WantTourProductResponse(WantTourProduct wantTourProduct) {
        this.id = wantTourProduct.getWantTourProductId();
        this.createAt = wantTourProduct.getCreateAt();
        this.title = wantTourProduct.getTitle().getTitle();
        this.content = wantTourProduct.getContent().getContent();
        this.locationResponses = wantTourProduct.getWantTourProductLocations().stream()
                .map(WantTourProductLocationResponse::new)
                .collect(Collectors.toList());
    }
}

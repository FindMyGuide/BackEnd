package com.find_my_guide.main_tour_product.tour_history_manager.dto;

import lombok.Data;

@Data
public class TourHistoryGuideRequest {
    private String email;

    private Long productId;
}

package com.find_my_guide.main_tour_product.tour_history_manager.repository;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryManagerGuideRequest;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
public class TourHistoryManagerDummyDataGenerator {

    private static final Random random = new Random();

    public TourHistoryManager generateRandomTourHistoryManager(Member guide, TourProduct tourProduct) {
        LocalDate tourStartDate = LocalDate.now().plusDays(random.nextInt(10));
        LocalDate tourEndDate = tourStartDate.plusDays(random.nextInt(5));

        TourHistoryManagerGuideRequest request = new TourHistoryManagerGuideRequest(guide.getIdx(), tourProduct.getTourProductId(), tourStartDate, tourEndDate);

        return request.toTourHistoryManager(guide, tourProduct);
    }
}

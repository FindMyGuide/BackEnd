package com.find_my_guide.main_tour_product.available_reservation_date.service;

import com.find_my_guide.main_tour_product.available_reservation_date.domain.AvailableDate;
import com.find_my_guide.main_tour_product.available_reservation_date.dto.AvailableDateRequest;
import com.find_my_guide.main_tour_product.available_reservation_date.dto.AvailableDateResponse;
import com.find_my_guide.main_tour_product.available_reservation_date.repository.AvailableDateRepository;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.repository.TourProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AvailableService {

    private final AvailableDateRepository availableDateRepository;

    private final TourProductRepository tourProductRepository;

    @Transactional
    public AvailableDateResponse registerDate(Long postId, AvailableDateRequest availableDateRequest) {
        TourProduct tourProduct = findById(postId);

        AvailableDate availableDate = availableDateRequest.toAvailableDateRequest();

        isDateReserved(tourProduct, availableDate);  // 예약 가능 여부 확인

        availableDate.addAvailableDate(tourProduct);

        availableDate.setReserved(true);  // 예약 상태 설정

        return new AvailableDateResponse(availableDateRepository.save(availableDate));
    }


    private void isDateReserved(TourProduct tourProduct, AvailableDate availableDate) {
        for (AvailableDate date : tourProduct.getAvailableDates()) {
            if (date.getDate().equals(availableDate.getDate()) && date.isReserved()) {
                throw new IllegalArgumentException("해당 날짜는 이미 예약되었습니다.");
            }
        }
    }


    private TourProduct findById(Long id) {
        return tourProductRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 관광 상품 입니다."));
    }


}

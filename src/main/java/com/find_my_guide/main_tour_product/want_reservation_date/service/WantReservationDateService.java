package com.find_my_guide.main_tour_product.want_reservation_date.service;

import com.find_my_guide.main_tour_product.want_reservation_date.domain.WantReservationDate;
import com.find_my_guide.main_tour_product.want_reservation_date.dto.WantReservationDateRequest;
import com.find_my_guide.main_tour_product.want_reservation_date.dto.WantReservationDateResponse;
import com.find_my_guide.main_tour_product.want_reservation_date.repository.WantReservationDateRepository;
import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import com.find_my_guide.main_tour_product.want_tour_product.repository.WantTourProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WantReservationDateService {

    private final WantTourProductRepository wantTourProductRepository;
    private final WantReservationDateRepository wantReservationDateRepository;


    @Transactional
    public WantReservationDateResponse registerDate(Long wantTourProductId, WantReservationDateRequest wantReservationDateRequest) {
        WantTourProduct wantTourProduct = findWantTourProduct(wantTourProductId);

        WantReservationDate wantReservationDate = wantReservationDateRequest.toWantReservationDate();

        isSameDate(wantTourProduct, wantReservationDate);

        wantReservationDate.addWantDate(wantTourProduct);

        return new WantReservationDateResponse(wantReservationDateRepository.save(wantReservationDate));
    }

    @Transactional
    public void deleteDate(Long wantTourProductId, Long wantReservationDateId) {

    }

    private void isSameDate(WantTourProduct wantTourProduct, WantReservationDate wantReservationDate) {
        if (wantTourProduct.getWantReservationDates().contains(wantReservationDate.getDate())) {
            throw new IllegalArgumentException("같은 날짜가 존재함");
        }
    }


    private WantTourProduct findWantTourProduct(Long id) {
        return wantTourProductRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 글 입니다."));
    }

    private WantReservationDate findWantReservation(Long id) {
        return wantReservationDateRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택하지 않은 날입니다."));
    }
}

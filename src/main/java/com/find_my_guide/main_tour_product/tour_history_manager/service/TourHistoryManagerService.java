package com.find_my_guide.main_tour_product.tour_history_manager.service;

import com.find_my_guide.main_member.common.NotFoundException;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import com.find_my_guide.main_tour_product.available_reservation_date.domain.AvailableDate;
import com.find_my_guide.main_tour_product.available_reservation_date.repository.AvailableDateRepository;
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryManagerGuideRequest;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryManagerResponse;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryTouristRequest;
import com.find_my_guide.main_tour_product.tour_history_manager.repository.TourHistoryManagerRepository;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductResponse;
import com.find_my_guide.main_tour_product.tour_product.repository.TourProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TourHistoryManagerService {

    private final TourProductRepository tourProductRepository;
    private final TourHistoryManagerRepository tourHistoryManagerRepository;
    private final AvailableDateRepository availableDateRepository; // 추가


    private final MemberRepository memberRepository;

    @Transactional
    public TourHistoryManagerResponse registerTourProductByGuide(String guideEmail, Long tourProductId) {
        TourProduct tourProduct = findTourProductById(tourProductId);
        Member guide = findMemberByEmail(guideEmail);

        TourHistoryManager tourHistoryManager = TourHistoryManager.builder()
                .guide(guide)
                .tourProduct(tourProduct)
                .build();

        tourHistoryManager.addTourProduct(tourProduct);
        tourHistoryManager.addGuide(guide);

        return new TourHistoryManagerResponse(tourHistoryManagerRepository.save(tourHistoryManager));
    }



    @Transactional
    public TourHistoryManagerResponse reserveTourByTourist(TourHistoryTouristRequest tourHistoryTouristRequest) {
        Member tourist = findMemberByEmail(tourHistoryTouristRequest.getEmail());
        TourProduct tourProduct = findTourProductById(tourHistoryTouristRequest.getProductId());

        checkIfTourDateIsAlreadyReservedForProduct(tourHistoryTouristRequest.getStartDate(), tourHistoryTouristRequest.getEndDate(), tourHistoryTouristRequest.getProductId());

        List<TourHistoryManager> tourHistoryManagerByTourProductId = findTourHistoryManagerByTourProductId(tourProduct.getTourProductId());

        Member guide = tourHistoryManagerByTourProductId.get(0).getGuide();


        TourHistoryManager newTourHistoryManager = TourHistoryManager.builder()
                .guide(guide)
                .tourist(tourist)
                .tourProduct(tourProduct)
                .tourStartDate(tourHistoryTouristRequest.getStartDate())
                .tourEndDate(tourHistoryTouristRequest.getEndDate())
                .build();

        removeAvailableDatesBetween(tourHistoryTouristRequest.getStartDate(),tourHistoryTouristRequest.getEndDate(),tourProduct.getTourProductId());

        TourHistoryManager savedTourHistoryManager = tourHistoryManagerRepository.save(newTourHistoryManager);

        TourHistoryManagerResponse tourHistoryManagerResponse = new TourHistoryManagerResponse(savedTourHistoryManager);


        tourHistoryManagerResponse.setGuideId(tourHistoryManagerByTourProductId.get(0).getGuide().getIdx());

        return tourHistoryManagerResponse;
    }

    public List<TourProductResponse> getTop10TourProductsByFrequency() {
        List<Long> top10TourProductIds = tourHistoryManagerRepository.findTop10TourProductIdsByFrequency();

        return top10TourProductIds.stream()
                .map(tourProductId -> {
                    TourProduct tourProduct = tourProductRepository.findById(tourProductId)
                            .orElseThrow(() -> new RuntimeException("TourProduct not found with ID: " + tourProductId));
                    return new TourProductResponse(tourProduct);
                })
                .collect(Collectors.toList());
    }

    private void checkIfTourDateIsAlreadyReservedForProduct(LocalDate startDate, LocalDate endDate, Long tourProductId) {
        List<TourHistoryManager> existingReservations = tourHistoryManagerRepository.findByTourStartDateAndTourEndDateAndTourProduct_TourProductId(startDate, endDate, tourProductId);

        if (!existingReservations.isEmpty()) {
            throw new IllegalArgumentException("이미 해당 날짜에 예약된 투어가 있습니다.");
        }
    }


    private void removeAvailableDatesBetween(LocalDate startDate, LocalDate endDate, Long tourProductId) {
        List<AvailableDate> datesToRemove = availableDateRepository.findAllByDateBetweenAndTourProduct_TourProductId(startDate, endDate, tourProductId);

        if (!datesToRemove.isEmpty()) {
            availableDateRepository.deleteAll(datesToRemove);
        }
    }

    private List<TourHistoryManager> findTourHistoryManagerByTourProductId(Long tourProductId) {
       return tourHistoryManagerRepository.findByTourProduct_TourProductId(tourProductId)
               .stream()
               .collect(Collectors.toList());

    }


    public boolean hasReservationHistory(Long memberId) {
        return !tourHistoryManagerRepository.findByGuide_Idx(memberId).isEmpty();
    }


    private Member findMemberByEmail(String guideEmail) {
        return memberRepository.findByEmail(guideEmail).orElseThrow(() -> new NotFoundException("존재하지 않는 회원"));
    }


    private TourProduct findTourProductById(Long id) {
        return tourProductRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 상품입니다."));
    }



}

package com.find_my_guide.main_tour_product.tour_history_manager.service;

import com.find_my_guide.main_member.common.NotFoundException;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import com.find_my_guide.main_tour_product.available_reservation_date.domain.AvailableDate;
import com.find_my_guide.main_tour_product.available_reservation_date.repository.AvailableDateRepository;
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryManagerResponse;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryTouristRequest;
import com.find_my_guide.main_tour_product.tour_history_manager.repository.TourHistoryManagerRepository;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductResponse;
import com.find_my_guide.main_tour_product.tour_product.repository.TourProductRepository;
import com.find_my_guide.main_tour_product.tour_product.service.TourProductService;
import com.find_my_guide.main_tour_product.tour_product_like.repository.TourProductLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TourHistoryManagerService {

    private final TourProductRepository tourProductRepository;
    private final TourHistoryManagerRepository tourHistoryManagerRepository;
    private final AvailableDateRepository availableDateRepository; // 추가
    private final TourProductLikeRepository tourProductLikeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public TourHistoryManagerResponse registerTourProductByGuide(String guideEmail, Long tourProductId) {
        TourProduct tourProduct = findTourProductById(tourProductId);
        Member guide = findMemberByEmail(guideEmail);

        TourHistoryManager tourHistoryManager = TourHistoryManager.builder()
                .guide(guide)
                .tourProduct(tourProduct)
                .isCompleted(false)
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

        removeAvailableDatesBetween(tourHistoryTouristRequest.getStartDate(), tourHistoryTouristRequest.getEndDate(), tourProduct.getTourProductId());

        TourHistoryManager savedTourHistoryManager = tourHistoryManagerRepository.save(newTourHistoryManager);

        TourHistoryManagerResponse tourHistoryManagerResponse = new TourHistoryManagerResponse(savedTourHistoryManager);


        tourHistoryManagerResponse.setGuideId(tourHistoryManagerByTourProductId.get(0).getGuide().getIdx());

        return tourHistoryManagerResponse;
    }

    @Transactional
    public void markToursAsCompleted() {
        LocalDate now = LocalDate.now();
        List<TourHistoryManager> tours = tourHistoryManagerRepository.findByTourEndDateBeforeAndIsCompletedFalse(now);
        for (TourHistoryManager tour : tours) {
            tour.setCompleted(true);
        }
    }



    public List<TourProductResponse> getCompletedToursForTourist(String touristEmail) {
        Member tourist = findMemberByEmail(touristEmail);
        List<TourHistoryManager> histories = tourHistoryManagerRepository.findByTourist(tourist);

        histories.forEach(TourHistoryManager::updateCompletionStatus);

        return histories.stream()
                .filter(TourHistoryManager::getIsCompleted)
                .map(history -> new TourProductResponse(history.getTourProduct()))
                .collect(Collectors.toList());
    }

    public List<TourProductResponse> getUpcomingToursForTourist(String touristEmail) {
        Member tourist = findMemberByEmail(touristEmail);
        List<TourHistoryManager> histories = tourHistoryManagerRepository.findByTourist(tourist);

        histories.forEach(TourHistoryManager::updateCompletionStatus);

        return histories.stream()
                .filter(history -> !history.getIsCompleted())
                .map(history -> new TourProductResponse(history.getTourProduct()))
                .collect(Collectors.toList());
    }




    public List<TourProductResponse> getTop10TourProductsByFrequency() {
        List<Long> top10TourProductIds = tourHistoryManagerRepository.findTop10TourProductIdsByFrequency();


        return top10TourProductIds.stream()
                .limit(10)
                .map(tourProductId -> {

                    long likes = tourProductLikeRepository.countByTourProduct_TourProductId(tourProductId);

                    TourProduct tourProduct = tourProductRepository.findById(tourProductId)
                            .orElseThrow(() -> new RuntimeException("관광상품이 없습니다. " + tourProductId));
                    TourProductResponse tourProductResponse = new TourProductResponse(tourProduct);

                    tourProductResponse.setLikes(likes);
                    return tourProductResponse;
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

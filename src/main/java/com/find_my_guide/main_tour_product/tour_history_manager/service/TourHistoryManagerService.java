package com.find_my_guide.main_tour_product.tour_history_manager.service;

import com.find_my_guide.main_member.common.NotFoundException;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import com.find_my_guide.main_tour_product.available_reservation_date.domain.AvailableDate;
import com.find_my_guide.main_tour_product.available_reservation_date.repository.AvailableDateRepository;
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryManagerResponse;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryTouristRequest;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.WantTourHistoryManagerResponse;
import com.find_my_guide.main_tour_product.tour_history_manager.repository.TourHistoryManagerRepository;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.dto.TourProductResponse;
import com.find_my_guide.main_tour_product.tour_product.repository.TourProductRepository;
import com.find_my_guide.main_tour_product.tour_product_like.repository.TourProductLikeRepository;
import com.find_my_guide.main_tour_product.want_reservation_date.domain.WantReservationDate;
import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import com.find_my_guide.main_tour_product.want_tour_product.repository.WantTourProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class TourHistoryManagerService {
    @PersistenceContext
    private EntityManager entityManager;


    private final TourProductRepository tourProductRepository;
    private final TourHistoryManagerRepository tourHistoryManagerRepository;
    private final AvailableDateRepository availableDateRepository;

    private final WantTourProductRepository wantTourProductRepository;
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
    public TourHistoryManagerResponse registerWantTourHistoryByGuide(String guideEmail, Long wantTourProductId) {
        WantTourProduct wantTourProduct = getWantTourProduct(wantTourProductId);
        Member guide = findMemberByEmail(guideEmail);

        TourHistoryManager byWantTourProduct = tourHistoryManagerRepository.findByWantTourProduct(wantTourProduct);

        byWantTourProduct.addGuide(guide);

        wantTourProduct.setReserved();

        wantTourProductRepository.save(wantTourProduct);


        return new TourHistoryManagerResponse(tourHistoryManagerRepository.save(byWantTourProduct));
    }

    @Transactional
    public TourHistoryManagerResponse registerTourProductByTourist(String touristEmail, Long wantTourProductId) {
        WantTourProduct wantTourProduct = getWantTourProduct(wantTourProductId);

        Member tourist = findMemberByEmail(touristEmail);

        List<WantReservationDate> reservationDatesList = wantTourProduct.getWantReservationDates();
        if (reservationDatesList == null || reservationDatesList.isEmpty()) {
            throw new IllegalStateException("예약 날짜가 존재하지 않습니다.");
        }

        List<LocalDate> dates = reservationDatesList.stream().map(WantReservationDate::getDate).collect(Collectors.toList());

        LocalDate tourStartDate = dates.stream().min(LocalDate::compareTo).orElseThrow(); // 가장 빠른 날짜
        LocalDate tourEndDate = dates.stream().max(LocalDate::compareTo).orElseThrow();   // 가장 늦은 날짜

        TourHistoryManager tourHistoryManager = TourHistoryManager.builder()
                .tourist(tourist)
                .wantTourProduct(wantTourProduct)
                .tourStartDate(tourStartDate)
                .tourEndDate(tourEndDate)
                .isCompleted(false)
                .build();

        tourHistoryManager.addWantTourProduct(wantTourProduct);
        tourHistoryManager.addTourist(tourist);

        return new TourHistoryManagerResponse(tourHistoryManagerRepository.save(tourHistoryManager));
    }

    @Transactional
    public void cancelReservation(String guideEmail, Long tourHistoryManagerId) {

        findMemberByEmail(guideEmail);

        TourHistoryManager tourHistoryManager = tourHistoryManagerRepository.findById(tourHistoryManagerId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약내역"));

        tourHistoryManagerRepository.delete(tourHistoryManager);
    }

    public List<TourHistoryManagerResponse> findAllReservedTourProductByGuide(String email){

        Member memberByEmail = findMemberByEmail(email);

        return tourHistoryManagerRepository.findReservationsByGuideId(memberByEmail.getIdx())
                .stream()
                .map(TourHistoryManagerResponse::new)
                .collect(Collectors.toList());
    }

    public List<WantTourHistoryManagerResponse> findAllReservedWantTourProductByGuide(String email){
        Member memberByEmail = findMemberByEmail(email);

        return tourHistoryManagerRepository.findWantTourReservationsByGuideId(memberByEmail.getIdx())
                .stream()
                .map(WantTourHistoryManagerResponse::new)
                .collect(Collectors.toList());
    }

    private WantTourProduct getWantTourProduct(Long wantTourProductId) {
        WantTourProduct wantTourProduct = wantTourProductRepository.findById(wantTourProductId).orElseThrow(() ->
                new NotFoundException("존재하지 않는 원해요 상품"));
        return wantTourProduct;
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

    public List<TourProductResponse> findToursByGuideEmail(String guideEmail) {
        Member memberByEmail = findMemberByEmail(guideEmail);
        List<TourHistoryManager> histories = tourHistoryManagerRepository.findByGuide(memberByEmail);
        return histories.stream()
                .map(history -> new TourProductResponse(history.getTourProduct()))
                .collect(Collectors.toList());
    }


    public List<TourProductResponse> getCompletedToursForTourist(String touristEmail) {
        Member tourist = findMemberByEmail(touristEmail);
        List<TourHistoryManager> histories = tourHistoryManagerRepository.findByTourist(tourist);


        return histories.stream()
                .filter(TourHistoryManager::getIsCompleted)
                .map(history -> new TourProductResponse(history.getTourProduct()))
                .collect(Collectors.toList());
    }

    public List<TourProductResponse> getUpcomingToursForTourist(String touristEmail) {
        Member tourist = findMemberByEmail(touristEmail);
        List<TourHistoryManager> histories = tourHistoryManagerRepository.findByTourist(tourist);

        return histories.stream()
                .filter(history -> history != null &&
                        Boolean.FALSE.equals(history.getIsCompleted()) &&
                        history.getTourEndDate() != null &&
                        history.getTourEndDate().isAfter(LocalDate.now()))
                .map(history -> {
                    List<LocalDate> reservedDates = generateDatesBetween(history.getTourStartDate(), history.getTourEndDate());
                    TourProductResponse tourProductResponse = new TourProductResponse(history.getTourProduct());
                    tourProductResponse.setReservedDates(reservedDates);

                    return tourProductResponse;
                })
                .collect(Collectors.toList());
    }


    private List<LocalDate> generateDatesBetween(LocalDate start, LocalDate end) {
        List<LocalDate> dates = new ArrayList<>();
        if (start == null || end == null) {
            return dates;
        }
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            dates.add(date);
        }
        return dates;
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

                    if (0 < likes) {
                        tourProductResponse.setLikeExist(true);
                    } else {
                        tourProductResponse.setLikeExist(false);
                    }
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

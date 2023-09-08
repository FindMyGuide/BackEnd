package com.find_my_guide.main_tour_product.tour_history_manager.service;

import com.find_my_guide.main_member.common.NotFoundException;
import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryManagerGuideRequest;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryManagerResponse;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryTouristRequest;
import com.find_my_guide.main_tour_product.tour_history_manager.repository.TourHistoryManagerRepository;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.repository.TourProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TourHistoryManagerService {

    private final TourProductRepository tourProductRepository;
    private final TourHistoryManagerRepository tourHistoryManagerRepository;

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
        TourHistoryManager existingTourHistoryManager = findTourHistoryManagerByTourProductId(tourHistoryTouristRequest.getProductId());

        if (existingTourHistoryManager.getTourist() != null) {
            throw new IllegalArgumentException("이 상품은 이미 예약되었습니다.");
        }

        existingTourHistoryManager.addTourist(tourist);
        existingTourHistoryManager.setTourStartDate(tourHistoryTouristRequest.getStartDate());
        existingTourHistoryManager.setTourEndDate(tourHistoryTouristRequest.getEndDate());

        return new TourHistoryManagerResponse(tourHistoryManagerRepository.save(existingTourHistoryManager));
    }

    private TourHistoryManager findTourHistoryManagerByTourProductId(Long tourProductId) {
       return tourHistoryManagerRepository.findByTourProduct_TourProductId(tourProductId).orElseThrow(() ->
               new IllegalArgumentException("존재하지 않는 상품입니다."));

    }


    public boolean hasReservationHistory(Long memberId) {
        return !tourHistoryManagerRepository.findByGuide_Idx(memberId).isEmpty();
    }


    private void isSameHistory(List<TourHistoryManager> tourProduct, TourHistoryManager tourHistoryManager) {
        if (tourProduct.contains(tourHistoryManager)) {
            throw new IllegalArgumentException("이미 같은 내역이 존재함");
        }
    }

    private Member findMemberByEmail(String guideEmail) {
        return memberRepository.findByEmail(guideEmail).orElseThrow(() -> new NotFoundException("존재하지 않는 회원"));
    }


    private TourProduct findTourProductById(Long id) {
        return tourProductRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 상품입니다."));
    }

    private Member findMemberById(String id) {
        return memberRepository.findByEmail(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 입니다."));
    }

    private TourHistoryManager findTourHistoryManagerById(Long id) {
        return tourHistoryManagerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예약 내역입니다."));
    }

}

package com.find_my_guide.main_tour_product.tour_history_manager.service;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_member.member.repository.MemberRepository;
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryManagerRequest;
import com.find_my_guide.main_tour_product.tour_history_manager.dto.TourHistoryManagerResponse;
import com.find_my_guide.main_tour_product.tour_history_manager.repository.TourHistoryManagerRepository;
import com.find_my_guide.main_tour_product.tour_product.domain.TourProduct;
import com.find_my_guide.main_tour_product.tour_product.repository.TourProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TourHistoryManagerService {

    private final TourProductRepository tourProductRepository;
    private final TourHistoryManagerRepository tourHistoryManagerRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public TourHistoryManagerResponse register(Long memberId, Long tourProductId) {
        TourProduct tourProduct = findTourProductById(tourProductId);

        TourHistoryManager tourHistoryManager = TourHistoryManager.builder()
                .member(findMemberById(memberId))
                .tourProduct(tourProduct)
                .build();

        isSameHistory(tourProduct.getTourHistoryManagers(), tourHistoryManager);

        tourHistoryManager.addTourProduct(tourProduct);
        tourHistoryManager.addMember(findMemberById(memberId));

        return new TourHistoryManagerResponse(tourHistoryManagerRepository.save(tourHistoryManager));
    }
    @Transactional
    public TourHistoryManagerResponse addTourStartEndDate(Long memberId, Long tourProductId, TourHistoryManagerRequest tourHistoryManagerRequest) {
        Member member = findMemberById(memberId);
        TourProduct tourProduct = findTourProductById(tourProductId);


        TourHistoryManager tourHistoryManager = TourHistoryManager.builder()
                .member(member)
                .tourProduct(tourProduct)
                .tourStartDate(tourHistoryManagerRequest.getTourStartDate())
                .tourEndDate(tourHistoryManagerRequest.getTourEndDate())
                .build();


        isSameHistory(tourProduct.getTourHistoryManagers(), tourHistoryManager);

        isSameHistory(member.getTourHistoryManagers(), tourHistoryManager);

        tourHistoryManagerRepository.save(tourHistoryManager);

        return new TourHistoryManagerResponse(tourHistoryManager);
    }

    private void isSameHistory(List<TourHistoryManager> tourProduct, TourHistoryManager tourHistoryManager) {
        if (tourProduct.contains(tourHistoryManager)) {
            throw new IllegalArgumentException("이미 같은 내역이 존재함");
        }
    }


    private TourProduct findTourProductById(Long id) {
        return tourProductRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 상품입니다."));
    }

    private Member findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 입니다."));
    }

}

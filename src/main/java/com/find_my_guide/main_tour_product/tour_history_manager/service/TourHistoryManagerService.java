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
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TourHistoryManagerService {

    private TourProductRepository tourProductRepository;
    private TourHistoryManagerRepository tourHistoryManagerRepository;

    private MemberRepository memberRepository;

    @Transactional
    public TourHistoryManagerResponse register(Long memberId, Long tourProductId, TourHistoryManagerRequest tourHistoryManagerRequest) {
        Member member = findMemberById(memberId);
        TourProduct tourProduct = findTourproductById(tourProductId);

        TourHistoryManager tourHistoryManager = tourHistoryManagerRequest.toTourHistoryManager();

        if (tourProduct.getTourHistoryManagers().contains(tourHistoryManager)) {
            throw new IllegalArgumentException("이미 같은 내역이 존재함");
        }

        tourHistoryManager.addTourProduct(tourProduct);
        tourHistoryManager.addMember(member);

        return new TourHistoryManagerResponse(tourHistoryManagerRepository.save(tourHistoryManager));
    }

    @Transactional
    public TourHistoryManagerResponse update(Long tourProductId, Long historyId, Long memberId, TourHistoryManagerRequest tourHistoryManagerRequest) {
        findTourproductById(tourProductId);

        TourHistoryManager manager = findTourHistoryManagerById(historyId);

        matchTourProduct_TourHistoryManager(tourProductId, manager);

        manager.update(tourHistoryManagerRequest.getTourStartDate(),
                tourHistoryManagerRequest.getTourEndDate(),
                tourHistoryManagerRequest.getGuideLanguage());

        return new TourHistoryManagerResponse(tourHistoryManagerRepository.save(manager));
    }

    public void matchTourProduct_TourHistoryManager(Long tourProductId,  TourHistoryManager tourHistoryManager) {
        if(!tourHistoryManager.getTourProduct().getTourProductId().equals(tourProductId)) {
            throw new IllegalArgumentException("내역이 상품에 속하지 않습니다.");
        }
    }


    private TourProduct findTourproductById(Long id) {
        return tourProductRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 상품입니다."));
    }

    private Member findMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 입니다."));
    }

    private TourHistoryManager findTourHistoryManagerById(Long managerId) {
        TourHistoryManager tourHistoryManager = tourHistoryManagerRepository.findById(managerId)
                .orElseThrow(() -> new IllegalArgumentException("manager does not exist"));
        return tourHistoryManager;
    }
}

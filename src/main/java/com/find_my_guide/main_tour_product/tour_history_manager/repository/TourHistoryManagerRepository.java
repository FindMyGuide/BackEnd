package com.find_my_guide.main_tour_product.tour_history_manager.repository;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import com.find_my_guide.main_tour_product.want_tour_product.domain.WantTourProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TourHistoryManagerRepository extends JpaRepository<TourHistoryManager, Long> {
    List<TourHistoryManager> findByTourProduct_TourProductId(Long aLong);

    List<TourHistoryManager> findByGuide_Idx(Long memberId);

    @Query("SELECT thm FROM TourHistoryManager thm WHERE thm.guide.idx = :guideId AND thm.tourStartDate IS NOT NULL AND thm.tourEndDate IS NOT NULL AND thm.isCompleted = false")
    List<TourHistoryManager> findReservationsByGuideId(@Param("guideId") Long guideId);

    @Query("SELECT thm FROM TourHistoryManager thm WHERE thm.guide.idx = :guideId AND thm.wantTourProduct IS NOT NULL AND thm.isCompleted = false")
    List<TourHistoryManager> findWantTourReservationsByGuideId(@Param("guideId") Long guideId);



    List<TourHistoryManager> findByTourStartDateAndTourEndDateAndTourProduct_TourProductId(LocalDate startDate, LocalDate endDate, Long tourProductId);

    @Query("SELECT thm.tourProduct.tourProductId FROM TourHistoryManager thm GROUP BY thm.tourProduct ORDER BY COUNT(thm.tourProduct) DESC")
    List<Long> findTop10TourProductIdsByFrequency();

    TourHistoryManager findByWantTourProduct(WantTourProduct wantTourProduct);
    List<TourHistoryManager> findByTourEndDateBeforeAndIsCompletedFalse(LocalDate endDate);

    List<TourHistoryManager> findByTourist(Member tourist);

    List<TourHistoryManager> findByGuide(Member guide);



}

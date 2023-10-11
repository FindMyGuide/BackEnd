package com.find_my_guide.main_tour_product.tour_history_manager.repository;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TourHistoryManagerRepository extends JpaRepository<TourHistoryManager, Long> {
    List<TourHistoryManager> findByTourProduct_TourProductId(Long aLong);

    List<TourHistoryManager> findByGuide_Idx(Long memberId);

    List<TourHistoryManager> findByTourStartDateAndTourEndDateAndTourProduct_TourProductId(LocalDate startDate, LocalDate endDate, Long tourProductId);

    @Query("SELECT thm.tourProduct.tourProductId FROM TourHistoryManager thm GROUP BY thm.tourProduct ORDER BY COUNT(thm.tourProduct) DESC")
    List<Long> findTop10TourProductIdsByFrequency();


    List<TourHistoryManager> findByTourEndDateBeforeAndIsCompletedFalse(LocalDate endDate);

    List<TourHistoryManager> findByTourist(Member tourist);

    List<TourHistoryManager> findByGuide(Member guide);



}

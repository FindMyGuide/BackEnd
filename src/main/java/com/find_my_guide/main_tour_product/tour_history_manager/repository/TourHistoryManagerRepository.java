package com.find_my_guide.main_tour_product.tour_history_manager.repository;

import com.find_my_guide.main_tour_product.tour_history_manager.domain.TourHistoryManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TourHistoryManagerRepository extends JpaRepository<TourHistoryManager, Long> {
    Optional<TourHistoryManager> findByTourProduct_TourProductId(Long aLong);

    List<TourHistoryManager> findByGuide_Idx(Long memberId);

}

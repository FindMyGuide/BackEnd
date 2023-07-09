package com.find_my_guide.main_tour_product.api.tourLocationDetail.repository;

import com.find_my_guide.main_tour_product.api.tourLocationDetail.domain.TourLocationDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourLocationDetailRepository extends JpaRepository<TourLocationDetail, Long> {
}

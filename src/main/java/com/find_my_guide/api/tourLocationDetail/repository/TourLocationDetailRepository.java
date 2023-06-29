package com.find_my_guide.api.tourLocationDetail.repository;

import com.find_my_guide.api.tourLocationDetail.domain.TourLocationDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourLocationDetailRepository extends JpaRepository<TourLocationDetail, Long> {
}

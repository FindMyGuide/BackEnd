package com.find_my_guide.available_reservation_date.repository;

import com.find_my_guide.available_reservation_date.domain.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvailableDateRepository extends JpaRepository<AvailableDate,Long> {
}

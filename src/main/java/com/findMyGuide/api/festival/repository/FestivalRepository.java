package com.findMyGuide.api.festival.repository;

import com.findMyGuide.api.festival.domain.Festival;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FestivalRepository extends JpaRepository<Festival, Long> {
}

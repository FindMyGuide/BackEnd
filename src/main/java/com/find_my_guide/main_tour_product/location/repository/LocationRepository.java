package com.find_my_guide.main_tour_product.location.repository;

import com.find_my_guide.main_tour_product.common.validation_field.Title;
import com.find_my_guide.main_tour_product.location.domain.Location;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l FROM Location l WHERE l.title.title LIKE :titleValue")
    List<Location> findByTitleContaining(@Param("titleValue") String titleValue);

}

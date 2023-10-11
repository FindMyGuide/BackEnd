package com.find_my_guide.main_tour_product.tour_product.repository;

import com.find_my_guide.main_tour_product.tour_product.domain.Images;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface ImagesRepository extends JpaRepository<Images, Long> {
}

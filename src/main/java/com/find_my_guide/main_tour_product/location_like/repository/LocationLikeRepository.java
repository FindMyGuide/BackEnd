package com.find_my_guide.main_tour_product.location_like.repository;

import com.find_my_guide.main_member.member.domain.entity.Member;
import com.find_my_guide.main_tour_product.location.domain.Location;
import com.find_my_guide.main_tour_product.location_like.domain.LocationLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationLikeRepository extends JpaRepository<LocationLike,Long> {
    Optional<LocationLike> findByLocationAndMember(Location location, Member member);

}

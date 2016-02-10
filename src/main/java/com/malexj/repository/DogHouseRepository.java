package com.malexj.repository;

import com.malexj.model.oneToOneMappingBidirectional.DogHouse;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by malex on 10.02.16.
 */
public interface DogHouseRepository extends JpaRepository<DogHouse, Long> {
}

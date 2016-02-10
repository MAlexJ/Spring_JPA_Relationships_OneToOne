package com.malexj.repository;

import com.malexj.model.oneToOneMappingBidirectional.Dog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by malex on 10.02.16.
 */
public interface DogRepository extends JpaRepository<Dog, Long> {
}

package com.malexj.repository;

import com.malexj.model.oneToOneMappinpWithCascadingAll.Breed;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by malex on 08.02.16.
 */
public interface BreedRepository extends JpaRepository<Breed, Long> {
}

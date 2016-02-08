package com.malexj.repository;

import com.malexj.model.oneToOneMappinpWithCascadingAll.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by malex on 08.02.16.
 */
public interface CatRepository extends JpaRepository<Cat, Long> {
}

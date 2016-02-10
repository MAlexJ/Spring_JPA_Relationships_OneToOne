package com.malexj.repository;

import com.malexj.model.oneToOneMappingPrimaryKeyAsJoinColumn.Flower;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by malex on 10.02.16.
 */
public interface FlowerRepository extends JpaRepository<Flower, Long> {
}

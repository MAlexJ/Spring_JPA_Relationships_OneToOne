package com.malexj.repository;

import com.malexj.model.oneToOneMappingWithFetchTypeLazy.NotebookStand;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by malex on 10.02.16.
 */
public interface NotebookStandRepository extends JpaRepository<NotebookStand, Long> {
}

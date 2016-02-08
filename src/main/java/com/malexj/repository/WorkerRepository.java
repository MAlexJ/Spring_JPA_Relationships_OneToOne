package com.malexj.repository;

import com.malexj.model.oneToOneUnidirectional.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by malex on 08.02.16.
 */
public interface WorkerRepository extends JpaRepository<Worker, Long> {
}

package com.malexj.repository;

import com.malexj.model.oneToOneUnidirectional.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by malex on 08.02.16.
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

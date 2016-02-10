package com.malexj.repository;

import com.malexj.model.bidirectionalOneToOneMapping.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by malex on 09.02.16.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
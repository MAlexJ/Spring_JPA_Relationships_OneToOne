package com.malexj.repository;

import com.malexj.model.retrieveOneToOneMappedEntity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by malex on 08.02.16.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
}

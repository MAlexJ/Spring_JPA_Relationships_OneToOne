package com.malexj.service;

import com.malexj.model.retrieveOneToOneMappedEntity.Student;

import java.util.List;

/**
 * Created by malex on 08.02.16.
 */
public interface StudentService {

    Student save(Student student);

    Student update(Student student);

    void delete(Long id);

    Student findById(Long id);

    List<Student> findAll();

}

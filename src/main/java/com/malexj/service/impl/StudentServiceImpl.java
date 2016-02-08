package com.malexj.service.impl;

import com.malexj.model.retrieveOneToOneMappedEntity.Student;
import com.malexj.repository.StudentRepository;
import com.malexj.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malex on 08.02.16.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public Student save(Student student) {
        return repository.saveAndFlush(student);
    }

    @Override
    public Student update(Student student) {
        return repository.saveAndFlush(student);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Student findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }
}

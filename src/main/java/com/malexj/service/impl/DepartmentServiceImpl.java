package com.malexj.service.impl;

import com.malexj.model.oneToOneUnidirectional.Department;
import com.malexj.repository.DepartmentRepository;
import com.malexj.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malex on 08.02.16.
 */

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Override
    public Department save(Department department) {
        return repository.saveAndFlush(department);
    }

    @Override
    public Department update(Department department) {
        return repository.saveAndFlush(department);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Department findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Department> findAll() {
        return repository.findAll();
    }
}

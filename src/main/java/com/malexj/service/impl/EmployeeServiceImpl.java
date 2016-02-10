package com.malexj.service.impl;

import com.malexj.model.bidirectionalOneToOneMapping.Employee;
import com.malexj.repository.EmployeeRepository;
import com.malexj.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malex on 09.02.16.
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee save(Employee employee) {
        return repository.saveAndFlush(employee);
    }

    @Override
    public Employee update(Employee employee) {
        return repository.saveAndFlush(employee);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Employee findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }
}

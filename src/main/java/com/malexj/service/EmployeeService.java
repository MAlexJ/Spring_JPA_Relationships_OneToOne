package com.malexj.service;

import com.malexj.model.bidirectionalOneToOneMapping.Employee;

import java.util.List;

/**
 * Created by malex on 09.02.16.
 */
public interface EmployeeService {

    Employee save(Employee employee);

    Employee update(Employee employee);

    void delete(Long id);

    Employee findById(Long id);

    List<Employee> findAll();
}

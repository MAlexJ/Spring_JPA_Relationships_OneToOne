package com.malexj.service;

import com.malexj.model.oneToOneUnidirectional.Department;

import java.util.List;

/**
 * Created by malex on 08.02.16.
 */
public interface DepartmentService {

    Department save(Department department);

    Department update(Department department);

    void delete(Long id);

    Department findById(Long id);

    List<Department> findAll();

}

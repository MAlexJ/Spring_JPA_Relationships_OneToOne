package com.malexj.service;

import com.malexj.model.oneToOneMappingWithFetchTypeLazy.Laptop;

import java.util.List;

/**
 * Created by malex on 10.02.16.
 */
public interface LaptopService {

    Laptop save(Laptop laptop);

    Laptop update(Laptop laptop);

    void delete(Long id);

    Laptop findById(Long id);

    List<Laptop> findAll();
}

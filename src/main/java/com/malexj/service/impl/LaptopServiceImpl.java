package com.malexj.service.impl;

import com.malexj.model.oneToOneMappingWithFetchTypeLazy.Laptop;
import com.malexj.repository.LaptopRepository;
import com.malexj.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malex on 10.02.16.
 */
@Service
public class LaptopServiceImpl implements LaptopService {

    @Autowired
    private LaptopRepository repository;

    @Override
    public Laptop save(Laptop laptop) {
        return repository.saveAndFlush(laptop);
    }

    @Override
    public Laptop update(Laptop laptop) {
        return repository.saveAndFlush(laptop);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Laptop findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Laptop> findAll() {
        return repository.findAll();
    }
}

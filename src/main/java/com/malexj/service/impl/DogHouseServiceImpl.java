package com.malexj.service.impl;

import com.malexj.model.oneToOneMappingBidirectional.DogHouse;
import com.malexj.repository.DogHouseRepository;
import com.malexj.service.DogHouseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by malex on 10.02.16.
 */
public class DogHouseServiceImpl implements DogHouseService {

    @Autowired
    private DogHouseRepository repository;

    @Override
    public DogHouse save(DogHouse house) {
        return repository.saveAndFlush(house);
    }

    @Override
    public DogHouse update(DogHouse house) {
        return repository.saveAndFlush(house);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public DogHouse findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<DogHouse> findAll() {
        return repository.findAll();
    }
}

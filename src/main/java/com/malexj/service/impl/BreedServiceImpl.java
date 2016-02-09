package com.malexj.service.impl;

import com.malexj.model.oneToOneMappinpWithCascadingAll.Breed;
import com.malexj.repository.BreedRepository;
import com.malexj.service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malex on 08.02.16.
 */

@Service
public class BreedServiceImpl implements BreedService {

    @Autowired
    private BreedRepository repository;

    @Override
    public Breed save(Breed breed) {
        return repository.saveAndFlush(breed);
    }

    @Override
    public Breed update(Breed breed) {
        return repository.saveAndFlush(breed);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Breed findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Breed> findAll() {
        return repository.findAll();
    }
}

package com.malexj.service.impl;

import com.malexj.model.oneToOneMappingBidirectional.Dog;
import com.malexj.repository.DogRepository;
import com.malexj.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malex on 10.02.16.
 */
@Service
public class DogServiceImpl implements DogService {

    @Autowired
    private DogRepository repository;

    @Override
    public Dog save(Dog dog) {
        return repository.saveAndFlush(dog);
    }

    @Override
    public Dog update(Dog dog) {
        return repository.saveAndFlush(dog);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Dog findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Dog> findAll() {
        return repository.findAll();
    }
}

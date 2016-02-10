package com.malexj.service;

import com.malexj.model.oneToOneMappingBidirectional.Dog;

import java.util.List;

/**
 * Created by malex on 10.02.16.
 */
public interface DogService {

    Dog save(Dog dog);

    Dog update(Dog dog);

    void delete(Long id);

    Dog findById(Long id);

    List<Dog> findAll();
}

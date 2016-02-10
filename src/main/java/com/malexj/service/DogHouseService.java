package com.malexj.service;

import com.malexj.model.oneToOneMappingBidirectional.DogHouse;

import java.util.List;

/**
 * Created by malex on 10.02.16.
 */
public interface DogHouseService {

    DogHouse save(DogHouse house);

    DogHouse update(DogHouse house);

    void delete(Long id);

    DogHouse findById(Long id);

    List<DogHouse> findAll();

}

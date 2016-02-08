package com.malexj.service;

import com.malexj.model.oneToOneMappinpWithCascadingAll.Breed;

import java.util.List;

/**
 * Created by malex on 08.02.16.
 */
public interface BreedService {

    Breed save(Breed breed);

    Breed update(Breed breed);

    void delete(Long id);

    Breed findById(Long id);

    List<Breed> findAll();

}

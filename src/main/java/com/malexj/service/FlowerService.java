package com.malexj.service;

import com.malexj.model.oneToOneMappingPrimaryKeyAsJoinColumn.Flower;

import java.util.List;

/**
 * Created by malex on 10.02.16.
 */
public interface FlowerService {

    Flower save(Flower flower);

    Flower update(Flower flower);

    void delete(Long id);

    Flower findById(Long id);

    List<Flower> findAll();
}

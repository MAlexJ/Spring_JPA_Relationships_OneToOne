package com.malexj.service.impl;

import com.malexj.model.oneToOneMappingPrimaryKeyAsJoinColumn.Flower;
import com.malexj.repository.FlowerRepository;
import com.malexj.service.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malex on 10.02.16.
 */

@Service
public class FlowerServiceImpl implements FlowerService {

    @Autowired
    private FlowerRepository repository;

    @Override
    public Flower save(Flower flower) {
        return repository.saveAndFlush(flower);
    }

    @Override
    public Flower update(Flower flower) {
        return repository.saveAndFlush(flower);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Flower findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Flower> findAll() {
        return repository.findAll();
    }
}

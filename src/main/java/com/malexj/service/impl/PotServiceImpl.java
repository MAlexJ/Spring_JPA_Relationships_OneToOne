package com.malexj.service.impl;

import com.malexj.model.oneToOneMappingPrimaryKeyAsJoinColumn.Pot;
import com.malexj.repository.PotRepository;
import com.malexj.service.PotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malex on 10.02.16.
 */
@Service
public class PotServiceImpl implements PotService {

    @Autowired
    private PotRepository repository;

    @Override
    public Pot save(Pot pot) {
        return repository.saveAndFlush(pot);
    }

    @Override
    public Pot update(Pot pot) {
        return repository.saveAndFlush(pot);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Pot findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Pot> findAll() {
        return repository.findAll();
    }
}

package com.malexj.service.impl;

import com.malexj.model.oto.corwin.calepin.Engine;
import com.malexj.repository.EngineRepository;
import com.malexj.service.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malex on 11.02.16.
 */

@Service
public class EngineServiceImpl implements EngineService {

    @Autowired
    private EngineRepository repository;

    @Override
    public Engine save(Engine engine) {
        return repository.saveAndFlush(engine);
    }

    @Override
    public Engine update(Engine engine) {
        return repository.saveAndFlush(engine);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Engine findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Engine> findAll() {
        return repository.findAll();
    }
}

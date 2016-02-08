package com.malexj.service.impl;

import com.malexj.model.oneToOneUnidirectional.Worker;
import com.malexj.repository.WorkerRepository;
import com.malexj.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malex on 08.02.16.
 */
@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    private WorkerRepository repository;

    @Override
    public Worker save(Worker worker) {
        return repository.saveAndFlush(worker);
    }

    @Override
    public Worker update(Worker worker) {
        return repository.saveAndFlush(worker);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Worker findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Worker> findAll() {
        return repository.findAll();
    }
}

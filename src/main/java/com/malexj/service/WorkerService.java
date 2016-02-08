package com.malexj.service;

import com.malexj.model.oneToOneUnidirectional.Worker;

import java.util.List;

/**
 * Created by malex on 08.02.16.
 */
public interface WorkerService {

    Worker save(Worker worker);

    Worker update(Worker worker);

    void delete(Long id);

    Worker findById(Long id);

    List<Worker> findAll();
}

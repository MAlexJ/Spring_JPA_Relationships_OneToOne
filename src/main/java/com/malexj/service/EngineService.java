package com.malexj.service;

import com.malexj.model.oto.corwin.calepin.Engine;

import java.util.List;

/**
 * Created by malex on 11.02.16.
 */
public interface EngineService {

    Engine save(Engine engine);

    Engine update(Engine engine);

    void delete(Long id);

    Engine findById(Long id);

    List<Engine> findAll();
}

package com.malexj.service.impl;

import com.malexj.model.oto.corwin.calepin.Car;
import com.malexj.repository.CarRepository;
import com.malexj.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by malex on 11.02.16.
 */

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository repository;

    @Override
    public Car save(Car car) {
        return repository.saveAndFlush(car);
    }

    @Override
    public Car update(Car car) {
        return repository.saveAndFlush(car);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Car findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Car> findAll() {
        return repository.findAll();
    }
}

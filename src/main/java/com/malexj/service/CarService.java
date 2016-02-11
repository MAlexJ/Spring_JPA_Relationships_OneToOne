package com.malexj.service;

import com.malexj.model.oto.corwin.calepin.Car;

import java.util.List;

/**
 * Created by malex on 11.02.16.
 */
public interface CarService {

    Car save(Car car);

    Car update(Car car);

    void delete(Long id);

    Car findById(Long id);

    List<Car> findAll();
}

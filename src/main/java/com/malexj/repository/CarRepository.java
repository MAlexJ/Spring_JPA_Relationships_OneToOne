package com.malexj.repository;

import com.malexj.model.oto.corwin.calepin.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by malex on 11.02.16.
 */
public interface CarRepository extends JpaRepository<Car, Long> {
}

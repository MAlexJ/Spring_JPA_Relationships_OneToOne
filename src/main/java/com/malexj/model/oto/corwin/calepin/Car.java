package com.malexj.model.oto.corwin.calepin;

import com.malexj.model.BaseEntity;

import javax.persistence.*;

/**
 * http://corwin.calepin.co/yet-another-trics-of-jpa-entities-design.html
 */

@Entity
@Table(name = "car")
public class Car extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL)
    private Engine engine;

    public Car(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        if (name != null ? !name.equals(car.name) : car.name != null) return false;
        return engine != null ? engine.equals(car.engine) : car.engine == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (engine != null ? engine.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", engine=" + engine +
                '}';
    }
}

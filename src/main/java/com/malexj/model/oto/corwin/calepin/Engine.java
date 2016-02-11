package com.malexj.model.oto.corwin.calepin;

import com.malexj.model.BaseEntity;

import javax.persistence.*;

/**
 * http://corwin.calepin.co/yet-another-trics-of-jpa-entities-design.html
 */

@Entity
@Table(name = "engine")
public class Engine extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private Car car;

    public Engine() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        if (name != null ? !name.equals(engine.name) : engine.name != null) return false;
        return car != null ? car.equals(engine.car) : engine.car == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (car != null ? car.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "name='" + name + '\'' +
                ", car=" + car +
                '}';
    }
}

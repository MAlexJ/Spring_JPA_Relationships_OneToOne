package com.malexj.model.oneToOneMappingBidirectional;

import com.malexj.model.BaseEntity;

import javax.persistence.*;

/**
 * Created by malex on 09.02.16.
 */

@Entity
@Table(name = "dog")
public class Dog extends BaseEntity {

    @Column(name = "name")
    private String userName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "house_id", unique = true, nullable = true, insertable = true, updatable = true)
    private DogHouse house;

    public Dog() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public DogHouse getHouse() {
        return house;
    }

    public void setHouse(DogHouse house) {
        this.house = house;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dog dog = (Dog) o;

        if (userName != null ? !userName.equals(dog.userName) : dog.userName != null) return false;
        return house != null ? house.equals(dog.house) : dog.house == null;

    }

    @Override
    public int hashCode() {
        int result = userName != null ? userName.hashCode() : 0;
        result = 31 * result + (house != null ? house.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "userName='" + userName + '\'' +
                ", house=" + house +
                '}';
    }
}

package com.malexj.model.oneToOneMappingBidirectional;

import com.malexj.model.BaseEntity;

import javax.persistence.*;

/**
 * Created by malex on 09.02.16.
 */
@Entity
@Table(name = "dog_house")
public class DogHouse extends BaseEntity{

    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "house")
    private Dog dog;

    public DogHouse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DogHouse dogHouse = (DogHouse) o;

        if (name != null ? !name.equals(dogHouse.name) : dogHouse.name != null) return false;
        return dog != null ? dog.equals(dogHouse.dog) : dogHouse.dog == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (dog != null ? dog.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DogHouse{" +
                "name='" + name + '\'' +
                ", dog=" + dog +
                '}';
    }
}

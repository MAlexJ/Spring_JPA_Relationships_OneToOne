package com.malexj.model.oneToOneMappinpWithCascadingAll;

import com.malexj.model.BaseEntity;

import javax.persistence.*;

/**
 * Created by malex on 08.02.16.
 */

@Entity
@Table(name = "cat")
public class Cat extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToOne(cascade= CascadeType.ALL)
    private Breed breed;

    public Cat() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cat cat = (Cat) o;

        if (name != null ? !name.equals(cat.name) : cat.name != null) return false;
        if (breed != null ? !breed.equals(cat.breed) : cat.breed != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (breed != null ? breed.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", breed=" + breed +
                '}';
    }
}

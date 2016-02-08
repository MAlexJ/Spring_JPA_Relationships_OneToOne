package com.malexj.model.oneToOneMappinpWithCascadingAll;

import com.malexj.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by malex on 08.02.16.
 */

@Entity
@Table(name = "breed")
public class Breed extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "state")
    private String state;

    public Breed() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Breed breed = (Breed) o;

        if (name != null ? !name.equals(breed.name) : breed.name != null) return false;
        return state != null ? state.equals(breed.state) : breed.state == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Breed{" +
                "name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}

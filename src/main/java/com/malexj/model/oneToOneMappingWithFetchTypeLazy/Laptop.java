package com.malexj.model.oneToOneMappingWithFetchTypeLazy;

import com.malexj.model.BaseEntity;

import javax.persistence.*;

/**
 * Created by malex on 10.02.16.
 */

@Entity
@Table(name = "laptop")
public class Laptop extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    private NotebookStand stand;

    public Laptop() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NotebookStand getStand() {
        return stand;
    }

    public void setStand(NotebookStand stand) {
        this.stand = stand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        if (name != null ? !name.equals(laptop.name) : laptop.name != null) return false;
        return stand != null ? stand.equals(laptop.stand) : laptop.stand == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (stand != null ? stand.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "name='" + name + '\'' +
                ", stand=" + stand +
                '}';
    }
}

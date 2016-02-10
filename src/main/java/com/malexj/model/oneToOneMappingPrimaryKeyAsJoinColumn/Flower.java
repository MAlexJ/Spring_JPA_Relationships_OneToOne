package com.malexj.model.oneToOneMappingPrimaryKeyAsJoinColumn;

import com.malexj.model.BaseEntity;

import javax.persistence.*;

/**
 * Created by malex on 10.02.16.
 */

@Entity
@Table(name = "flower")
public class Flower extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "flower1")
    private Pot pot;

    public Flower() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pot getPot() {
        return pot;
    }

    public void setPot(Pot pot) {
        this.pot = pot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flower flower = (Flower) o;

        if (name != null ? !name.equals(flower.name) : flower.name != null) return false;
        return pot != null ? pot.equals(flower.pot) : flower.pot == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (pot != null ? pot.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "name='" + name + '\'' +
                ", pot=" + pot +
                '}';
    }
}

package com.malexj.model.oneToOneMappingPrimaryKeyAsJoinColumn;

import com.malexj.model.BaseEntity;

import javax.persistence.*;

/**
 * Created by malex on 10.02.16.
 */

@Entity
@Table(name = "pot")
public class Pot extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToOne
    //a primary key column that is used as a foreign key to join to another table
    @PrimaryKeyJoinColumn
    private Flower flower1;

    public Pot() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Flower getFlower1() {
        return flower1;
    }

    public void setFlower1(Flower flower1) {
        this.flower1 = flower1;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pot pot = (Pot) o;

        if (name != null ? !name.equals(pot.name) : pot.name != null) return false;
        return flower1 != null ? flower1.equals(pot.flower1) : pot.flower1 == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (flower1 != null ? flower1.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pot{" +
                "name='" + name + '\'' +
                ", flower1=" + flower1 +
                '}';
    }
}

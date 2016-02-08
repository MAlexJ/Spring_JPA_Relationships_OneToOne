package com.malexj.model.oneToOneUnidirectional;

import com.malexj.model.BaseEntity;

import javax.persistence.*;

/**
 * Created by malex on 08.02.16.
 */
@Entity
@Table(name = "worker")
public class Worker extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="DEPT_ID", unique= true, nullable=true, insertable=true, updatable=true)
    private Department department;

    public Worker() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Worker worker = (Worker) o;

        if (name != null ? !name.equals(worker.name) : worker.name != null) return false;
        if (department != null ? !department.equals(worker.department) : worker.department != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (department != null ? department.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", department=" + department +
                '}';
    }
}

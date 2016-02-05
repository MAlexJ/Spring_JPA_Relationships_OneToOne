package com.malexj.model;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {

    @Id
    @SequenceGenerator(name = "store_sequence", sequenceName = "store_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "store_sequence")
    private Long id;

    public BaseEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

package com.malexj.model.bidirectionalOneToOneMapping_02;

import com.malexj.model.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity {

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = {CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User user;

    public Account() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (name != null ? !name.equals(account.name) : account.name != null) return false;
        if (user != null ? !user.equals(account.user) : account.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (user != null ? 31 : 0);
        return result;
    }

}

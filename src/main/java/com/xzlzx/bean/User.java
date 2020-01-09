package com.xzlzx.bean;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class User extends PanacheEntity {

    public String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }
}

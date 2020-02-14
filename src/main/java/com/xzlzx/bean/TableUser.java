package com.xzlzx.bean;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class TableUser extends PanacheEntity {

    public String name;

    public LocalDateTime createTime;

    public TableUser() {
    }

    public TableUser(String name) {
        this.name = name;
    }
}

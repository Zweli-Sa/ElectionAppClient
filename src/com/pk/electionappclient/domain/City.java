package com.pk.electionappclient.domain;


import sun.jvm.hotspot.oops.CellTypeState;

import java.io.Serializable;
import java.util.List;

public class City implements Serializable {
    private long id;
    private String name;
    private List<User> users = null;

    public City() {
    }

    public City(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public City(long id, String name, List<User> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

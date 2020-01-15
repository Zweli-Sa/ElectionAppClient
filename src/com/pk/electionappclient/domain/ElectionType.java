package com.pk.electionappclient.domain;

import java.io.Serializable;
import java.util.List;

public class ElectionType implements Serializable {

    private Long id;
    private String name;
    private List<Election> elections;

    public ElectionType() {
    }

    public ElectionType(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ElectionType(Long id, String name, List<Election> elections) {
        this.id = id;
        this.name = name;
        this.elections = elections;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Election> getElections() {
        return elections;
    }

    public void setElections(List<Election> elections) {
        this.elections = elections;
    }

    @Override
    public String toString() {
        return "ElectionType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

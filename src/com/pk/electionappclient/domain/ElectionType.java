package com.pk.electionappclient.domain;

import java.util.List;

public class ElectionType {

    private Long id;
    private String name;
//    private List<Election> elections;

    public ElectionType(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "ElectionType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

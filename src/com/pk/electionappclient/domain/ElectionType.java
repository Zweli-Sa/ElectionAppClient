package com.pk.electionappclient.domain;

public class ElectionType {

    private long id;

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

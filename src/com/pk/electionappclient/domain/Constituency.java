package com.pk.electionappclient.domain;

import java.util.List;


public class Constituency {

    private Long id;
    private String name;
    private String description;

    public Constituency() {
    }

    public Constituency(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Constituency(long id, String name, String description, Election election, List<ElectionList> electionLists) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Constituency{" +
                "id=" + id +
                '}';
    }
}

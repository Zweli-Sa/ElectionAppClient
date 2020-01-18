package com.pk.electionappclient.domain;

import java.util.ArrayList;
import java.util.List;

public class ElectionList {

    private Long id;
    private String name;
    private String description;

    public ElectionList() {
    }

    public ElectionList(long id, List<Candidate> candidates) {
        this.id = id;
    }

    public ElectionList(long id, List<Candidate> candidates, ElectoralParty electoralParty, Constituency constituency) {
        this.id = id;
    }///dodane constituency

    public ElectionList(long id, String name, List<Candidate> candidates) {
        this.id = id;
        this.name = name;
    }

    public ElectionList(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "ElectionList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

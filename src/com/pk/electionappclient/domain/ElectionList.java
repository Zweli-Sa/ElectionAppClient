package com.pk.electionappclient.domain;

import java.util.List;

public class ElectionList {

    private long id;

    private String name;

    private String description;

//    private Constituency constituency;

    private List<Candidate> candidates;

    public ElectionList(long id, List<Candidate> candidates) {
        this.id = id;
        this.candidates = candidates;
    }

    public ElectionList(long id, String name, List<Candidate> candidates) {
        this.id = id;
        this.name = name;
        this.candidates = candidates;
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

    public List<Candidate> getCandidates() {
        return candidates;
    }

    @Override
    public String toString() {
        return "ElectionList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", candidates=" + candidates +
                '}';
    }
}

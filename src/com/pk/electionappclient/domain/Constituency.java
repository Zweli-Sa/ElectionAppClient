package com.pk.electionappclient.domain;

import java.util.List;

public class Constituency {

    private Long id;

    private String name;

    private String description;

    private Election election;

    private List<ElectionList> electionLists;

    private List<City> cityList;

    public Constituency(long id, String name, String description, Election election, List<ElectionList> electionLists) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.election = election;
        this.electionLists = electionLists;
    }

    public Constituency() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Election getElection() {
        return election;
    }

    public List<ElectionList> getElectionLists() {
        return electionLists;
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

    public void setElection(Election election) {
        this.election = election;
    }

    public void setElectionLists(List<ElectionList> electionLists) {
        this.electionLists = electionLists;
    }
}

package com.pk.electionappclient.domain;

import java.util.ArrayList;
import java.util.List;

public class ElectionList {

    private Long id;
    private String name;
    private String description;
    private Constituency constituency;
    private List<Candidate> candidates;
    private ElectoralParty electoralParty;


//    private City city;



    public ElectionList(long id, List<Candidate> candidates) {
        this.id = id;
        this.candidates = candidates;
        this.electoralParty = electoralParty;
    }

    public ElectionList(long id, List<Candidate> candidates, ElectoralParty electoralParty, Constituency constituency) {
        this.id = id;
        this.candidates = candidates;
        this.electoralParty = electoralParty;
        this.constituency = constituency;
    }///dodane constituency

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

    public ElectoralParty getElectoralParty() {
        return electoralParty;
    }

    public void setElectoralParty(ElectoralParty electoralParty) {
        this.electoralParty = electoralParty;
    }

    public Constituency getConstituency() {
        return constituency;
    }

    public void setConstituency(Constituency constituency) {
        this.constituency = constituency;
    }

    @Override
    public String toString() {
        return "ElectionList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", constituency=" + constituency.getId() +
                ", candidates=" + candidates +
                ", electoralParty=" + electoralParty +
                '}';
    }
}

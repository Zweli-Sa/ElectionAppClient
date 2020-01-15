package com.pk.electionappclient.domain;

import java.io.Serializable;
import java.util.List;

public class ElectoralParty implements Serializable {

    private Long id;
    private String name;
    private String description;
    private List<Candidate> candidates = null;
    private ElectoralProgramme electoralProgramme = null;

    public ElectoralParty() {
    }

    public ElectoralParty(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ElectoralParty(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public ElectoralParty(long id, String name, String description, List<Candidate> candidates, ElectoralProgramme electoralProgramme) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.candidates = candidates;
        this.electoralProgramme = electoralProgramme;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public ElectoralProgramme getElectoralProgramme() {
        return electoralProgramme;
    }

    public void setElectoralProgramme(ElectoralProgramme electoralProgramme) {
        this.electoralProgramme = electoralProgramme;
    }

    @Override
    public String toString() {
        return name;
    }
}

package com.pk.electionappclient.domain;

import java.util.List;

public class ElectoralParty {

    private Long id;
    private String name;
    private String description;
    private List<Candidate> candidates = null;

    private ElectoralProgramme electoralProgramme = null;



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

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

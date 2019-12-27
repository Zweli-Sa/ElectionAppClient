package com.pk.electionappclient.domain;

public class ElectoralParty {

    private long id;

    private String name;

    private String description;

//    private List<Candidate> candidates;

//    private ElectoralProgramme electoralProgramme;



    public ElectoralParty(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ElectoralParty(long id, String name, String description) {
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

    @Override
    public String toString() {
        return name;
    }
}

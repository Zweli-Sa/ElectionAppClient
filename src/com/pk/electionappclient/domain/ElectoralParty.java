package com.pk.electionappclient.domain;

public class ElectoralParty {

    private long id;

    private String name;

    private String description;

//    private List<Candidate> candidates;

//    private ElectoralProgramme electoralProgramme;

    public ElectoralParty(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}

package com.pk.electionappclient.domain;

public class ElectoralProgramme {

    private long id;

    private String description;

//    private ElectoralParty electoralParty;

    public ElectoralProgramme(long id, String description) {
        this.id = id;
        this.description = description;
    }
}

package com.pk.electionappclient.domain;

import java.io.Serializable;

public class ElectoralProgramme implements Serializable {

    private Long id;
    private String description;
    private ElectoralParty electoralParty;

    public ElectoralProgramme() {}

    public ElectoralProgramme(Long id, String description, ElectoralParty electoralParty) {
        this.id = id;
        this.description = description;
        this.electoralParty = electoralParty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ElectoralParty getElectoralParty() {
        return electoralParty;
    }

    public void setElectoralParty(ElectoralParty electoralParty) {
        this.electoralParty = electoralParty;
    }
}

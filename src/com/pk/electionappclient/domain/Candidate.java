package com.pk.electionappclient.domain;

public class Candidate {

    private long id;

    private String name;

    private String lastname;

    private String education;

    private String placeOfResidence;
//
//    private ElectionList electionList;
//
//    private ElectoralParty electoralParty;

    public Candidate(long id, String name, String lastname, String education, String placeOfResidence) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.education = education;
        this.placeOfResidence = placeOfResidence;
    }
}

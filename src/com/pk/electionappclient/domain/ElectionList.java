package com.pk.electionappclient.domain;

public class ElectionList {

    private long id;

    private String name;

    private String description;

//    private Constituency constituency;

//    private List<Candidate> candidates;

    public ElectionList(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}

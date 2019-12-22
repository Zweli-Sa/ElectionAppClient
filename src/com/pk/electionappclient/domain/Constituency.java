package com.pk.electionappclient.domain;

public class Constituency {

    private long id;

    private String name;

    private String description;

//    private Election election;
//
//    private List<ElectionList> electionLists;

    public Constituency(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}

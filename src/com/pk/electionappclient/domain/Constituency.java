package com.pk.electionappclient.domain;

import java.util.List;


public class Constituency {

    private Long id;
    private String name;
    private String description;
    private Election election;
    private List<ElectionList> electionLists;
}

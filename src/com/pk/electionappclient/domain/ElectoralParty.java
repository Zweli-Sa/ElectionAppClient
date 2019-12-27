package com.pk.electionappclient.domain;

import java.util.List;

public class ElectoralParty {

    private Long id;
    private String name;
    private String description;
    private List<Candidate> candidates;
    private ElectoralProgramme electoralProgramme;
}

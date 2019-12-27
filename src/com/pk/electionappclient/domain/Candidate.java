package com.pk.electionappclient.domain;

import java.util.List;


public class Candidate {
    private Long id;
    private String name;
    private String lastname;
    private String education;
    private String placeOfResidence;
    private List<VoteResult> voteResults;
    private ElectionList electionList;
    private ElectoralParty electoralParty;
}

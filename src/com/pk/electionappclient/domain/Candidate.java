package com.pk.electionappclient.domain;

import java.io.Serializable;
import java.util.List;

public class Candidate implements Serializable {

    private Long id;

    private String name;

    private String lastname;

    private String education;

    private String placeOfResidence;

    protected List<VoteResult> voteResults;

    private ElectionList electionList;

    private ElectoralParty electoralParty;

    public Candidate(long id, String name, String lastname, String education, String placeOfResidence,
                    List<VoteResult> voteResults, ElectionList electionList, ElectoralParty electoralParty) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.education = education;
        this.placeOfResidence = placeOfResidence;
        this.voteResults = voteResults;
        this.electionList = electionList;
        this.electoralParty = electoralParty;
    }

    public Candidate() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEducation() {
        return education;
    }

    public String getPlaceOfResidence() {
        return placeOfResidence;
    }

    public List<VoteResult> getVoteResults() {
        return voteResults;
    }

    public ElectionList getElectionList() {
        return electionList;
    }

    public ElectoralParty getElectoralParty() {
        return electoralParty;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setPlaceOfResidence(String placeOfResidence) {
        this.placeOfResidence = placeOfResidence;
    }

    public void setVoteResults(List<VoteResult> voteResults) {
        this.voteResults = voteResults;
    }

    public void setElectionList(ElectionList electionList) {
        this.electionList = electionList;
    }

    public void setElectoralParty(ElectoralParty electoralParty) {
        this.electoralParty = electoralParty;
    }
}

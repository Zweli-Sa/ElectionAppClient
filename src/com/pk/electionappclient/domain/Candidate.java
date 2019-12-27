package com.pk.electionappclient.domain;

import java.io.Serializable;
import java.util.List;

public class Candidate {

    private Long id;

    private String name;

    private String lastName;

    private String education;

    private String placeOfResidence;

    protected List<VoteResult> voteResults;

    private ElectionList electionList;

    private ElectoralParty electoralParty;

    public Candidate(long l, String name, String lastName, String education, String placeOfResidence, ElectoralParty electoralParty) {
        this.id = l;
        this.name = name;
        this.lastName = lastName;
        this.education = education;
        this.placeOfResidence = placeOfResidence;
        this.electoralParty = electoralParty;
    }


    public Candidate(long id, String name, String lastname, String education, String placeOfResidence,
                     List<VoteResult> voteResults, ElectionList electionList, ElectoralParty electoralParty) {
        this.id = id;
        this.name = name;
        this.lastName = lastname;
        this.education = education;
        this.placeOfResidence = placeOfResidence;
        this.voteResults = voteResults;
        this.electionList = electionList;
        this.electoralParty = electoralParty;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastName;
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
        this.lastName = lastname;
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

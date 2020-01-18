package com.pk.electionappclient.domain;

import java.io.Serializable;
import java.util.List;


public class Candidate implements Serializable {
    private Long id;
    private String name;
    private String lastname;
    private String education;
    private String placeOfResidence;

    public Candidate() {
    }

//    public Candidate(long l, String name, String lastName, Education education, String placeOfResidence, ElectoralParty electoralParty) {
//        this.id = l;
//        this.name = name;
//        this.lastname = lastName;
//        this.education = education;
//        this.placeOfResidence = placeOfResidence;
//        this.electoralParty = electoralParty;
//    }
//
//
//    public Candidate(long id, String name, String lastname, Education education, String placeOfResidence,
//                     List<VoteResult> voteResults, ElectionList electionList, ElectoralParty electoralParty) {
//        this.id = id;
//        this.name = name;
//        this.lastname = lastname;
//        this.education = education;
//        this.placeOfResidence = placeOfResidence;
//        this.voteResults = voteResults;
//        this.electionList = electionList;
//        this.electoralParty = electoralParty;
//    }
//
//    public Candidate(Long id, String name, String lastname, Education education, String placeOfResidence, List<VoteResult> voteResults, ElectionList electionList, ElectoralParty electoralParty) {
//        this.id = id;
//        this.name = name;
//        this.lastname = lastname;
//        this.education = education;
//        this.placeOfResidence = placeOfResidence;
//        this.voteResults = voteResults;
//        this.electionList = electionList;
//        this.electoralParty = electoralParty;
//    }


    public Candidate(Long id, String name, String lastname, String education, String placeOfResidence) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.education = education;
        this.placeOfResidence = placeOfResidence;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPlaceOfResidence() {
        return placeOfResidence;
    }

    public void setPlaceOfResidence(String placeOfResidence) {
        this.placeOfResidence = placeOfResidence;
    }


    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastname + '\'' +
                ", education=" + education +
                ", placeOfResidence='" + placeOfResidence + '\'' +
                '}';
    }
}

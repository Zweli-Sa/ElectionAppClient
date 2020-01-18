package com.pk.electionappclient.domain;

import com.sun.org.apache.regexp.internal.RE;

import java.io.Serializable;


public class Candidate implements Serializable {
    private Long id;
    private String name;
    private String lastname;
    private String education;
    private String placeOfResidence;

    public Candidate() {
    }

    public Candidate(long id, String name, String lastname, String education, String placeOfResidence) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.education = education;
        this.placeOfResidence = placeOfResidence;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public Education getEducation() {

        switch (this.education.toLowerCase()) {
            case "PODSTAWOWE":
                return Education.PODSTAWOWE;
            case "ZAWODOWE":
                return Education.ZAWODOWE;
            case "SREDNIE":
                return Education.SREDNIE;
            case "LICENCJAT":
                return Education.LICENCJAT;
            case "INZYNIER":
                return Education.INZYNIER;
            case "MAGISTER":
                return Education.MAGISTER;
            case "DOKTOR":
                return Education.DOKTOR;
            case "PROFESOR;":
                return Education.PROFESOR;
        }
        return null;
    }

    public String getPlaceOfResidence() {
        return placeOfResidence;
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

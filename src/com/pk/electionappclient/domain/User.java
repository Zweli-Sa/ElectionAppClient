package com.pk.electionappclient.domain;

import java.math.BigInteger;

public class User {

    private long id;

    private String name;

    private String lastname;

    private String street;

    private long number;

    private long flatNumber;

    private String city;

    private String postcode;

    private String pesel;

    private BigInteger idNumber;

    private String email;

    private String phoneNumber;

//    private List<Report> reports;

//    private List<VoteResult> voteResults;

    public User(long id, String name, String lastname, String street, long number, long flatNumber,
                String city, String postcode, String pesel, BigInteger idNumber, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.street = street;
        this.number = number;
        this.flatNumber = flatNumber;
        this.city = city;
        this.postcode = postcode;
        this.pesel = pesel;
        this.idNumber = idNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
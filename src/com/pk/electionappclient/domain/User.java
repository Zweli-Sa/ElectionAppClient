package com.pk.electionappclient.domain;

import java.math.BigInteger;
import java.util.List;

public class User {

    private Long id;
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
    private List<Report> reports;
    private List<VoteResult> voteResults;
}

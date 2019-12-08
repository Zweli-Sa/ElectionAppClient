package com.pk.electionappclient.domain;

import java.time.LocalDateTime;


public class VoteResult {

    private long id;

//    private User user;
//
//    private Election election;
//
//    private Candidate candidate;

    private LocalDateTime voteTime;

    public VoteResult(long id, LocalDateTime voteTime) {
        this.id = id;
        this.voteTime = voteTime;
    }
}

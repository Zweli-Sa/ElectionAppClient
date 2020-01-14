package com.pk.electionappclient.domain;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.time.LocalDateTime;
import java.util.List;

public class VoteResult {
    private long id;

//    private User user;
//
    private Election election;
//
    private Candidate candidate;

    private Constituency constituency;

    private LocalDateTime voteTime;

    public VoteResult(long id, Election election, Candidate candidate,Constituency constituency, LocalDateTime voteTime) {
        this.id = id;
        this.election = election;
        this.voteTime = voteTime;
        this.candidate = candidate;
        this.constituency = constituency;
    }

    public VoteResult(long id, LocalDateTime voteTime) {
        this.id = id;
        this.voteTime = voteTime;
    }


    @Override
    public String toString() {
        return "VoteResult{" +
                "id=" + id +
                ", election=" + election.getId() +
                "candidate: " + candidate +
                "constituency: "+ constituency +
                '}';
    }
}

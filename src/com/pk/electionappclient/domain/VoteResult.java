package com.pk.electionappclient.domain;

import java.time.LocalDateTime;
import java.util.List;

public class VoteResult {
    private long id;

//    private User user;
//
    private Election election;
//
//    private Candidate candidate;

    private LocalDateTime voteTime;

    public VoteResult(long id, Election election) {
        this.id = id;
        this.election = election;
    }

    public VoteResult(long id, LocalDateTime voteTime) {
        this.id = id;
        this.voteTime = voteTime;
    }

    @Override
    public String toString() {
        return "VoteResult{" +
                "id=" + id +
                ", election=" + election +
                '}';
    }
}

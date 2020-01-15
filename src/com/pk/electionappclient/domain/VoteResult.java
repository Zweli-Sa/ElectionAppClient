package com.pk.electionappclient.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class VoteResult implements Serializable {
    private long id;

    private User user;

    private Election election;

    private Candidate candidate;

    private LocalDateTime voteTime;

    public VoteResult() {
    }

    public VoteResult(long id, User user, Election election, Candidate candidate, LocalDateTime voteTime) {
        this.id = id;
        this.user = user;
        this.election = election;
        this.candidate = candidate;
        this.voteTime = voteTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public LocalDateTime getVoteTime() {
        return voteTime;
    }

    public void setVoteTime(LocalDateTime voteTime) {
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

package com.pk.electionappclient.domain;

public class VoteResultsCandidate {
    private Candidate candidate;
    private int sum;

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public VoteResultsCandidate(Candidate candidate, int sum) {
        this.candidate = candidate;
        this.sum = sum;
    }
}

package com.pk.electionappclient.domain;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.time.LocalDateTime;
import java.util.List;

public class VoteResult {
    private long id;
    private LocalDateTime voteTime;

    public VoteResult() {
    }

    public VoteResult(long id, LocalDateTime voteTime) {
        this.id = id;
        this.voteTime = voteTime;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
                '}';
    }
}

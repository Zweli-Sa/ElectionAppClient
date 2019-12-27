package com.pk.electionappclient.domain;

import java.time.LocalDateTime;
import java.util.List;

public class VoteResult {

    private Long id;
    private User user;
    private Election election;
    private List<Candidate> candidates;
    private LocalDateTime voteTime;
}

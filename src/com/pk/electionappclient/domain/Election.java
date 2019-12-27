package com.pk.electionappclient.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Election {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private List<VoteResult> voteResults;
    private ElectionType electionType;
    private List<Constituency> constituencies;
}

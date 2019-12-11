package com.pk.electionappclient.domain;

import java.time.LocalDateTime;

public class Election {

    private long id;

    private LocalDateTime startDate;

    private LocalDateTime finishDate;

//    private ElectionType electionType;

//    private List<Constituency> constituencies;

    public Election(long id, LocalDateTime startDate, LocalDateTime finishDate) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }
}

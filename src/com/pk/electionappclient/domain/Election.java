package com.pk.electionappclient.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Election {

    private long id;

    private LocalDateTime startDate;

    private LocalDateTime finishDate;

    private ElectionType electionType;

    private List<ElectionList> ListElectionList;

//    private List<Constituency> constituencies;


    public Election(long id, LocalDateTime startDate, LocalDateTime finishDate) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public Election(long id, LocalDateTime startDate, LocalDateTime finishDate, ElectionType electionType, List<ElectionList> electionList) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.electionType = electionType;
        this.ListElectionList = electionList;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public ElectionType getElectionType() {
        return electionType;
    }


    public List<ElectionList> getListElectionList() {
        return ListElectionList;
    }

    @Override
    public String toString() {
        return "Election{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", electionType=" + electionType +
                ", ListElectionList=" + ListElectionList +
                '}';
    }
}

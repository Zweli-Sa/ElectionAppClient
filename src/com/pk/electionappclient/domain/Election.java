package com.pk.electionappclient.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Election {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;

    private ElectionType electionType;

    private List<ElectionList> ListElectionList;

    private Boolean isActive;

    private String electionName;

    private List<Constituency> constituencies;


    public Election(long id, Boolean isActive, String electionName) {
        this.id = id;
        this.isActive = isActive;
        this.electionName = electionName;
    }

    public Election(long id, LocalDateTime startDate, LocalDateTime finishDate) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public Election(long id, LocalDateTime startDate, LocalDateTime finishDate, ElectionType electionType,List<ElectionList> electionList, Boolean isActive, String electionName) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.electionType = electionType;
        this.isActive = isActive;
        this.ListElectionList = electionList;
        this.electionName = electionName;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getElectionName() {
        return electionName;
    }

    public void setElectionName(String electionName) {
        this.electionName = electionName;
    }

    public List<ElectionList> getListElectionList() {
        return ListElectionList;
    }

    public void setListElectionList(List<ElectionList> listElectionList) {
        ListElectionList = listElectionList;
    }

    public List<Constituency> getConstituencies() {
        return constituencies;
    }

    public void setConstituencies(List<Constituency> constituencies) {
        this.constituencies = constituencies;
    }

    @Override
    public String toString() {
        return "Election{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", electionType=" + electionType +
                ", ListElectionList=" + ListElectionList +
                ", isActive=" + isActive +
                ", electionName='" + electionName + '\'' +
                ", constituencies=" + constituencies +
                '}';
    }
}

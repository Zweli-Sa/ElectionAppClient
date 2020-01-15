package com.pk.electionappclient.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Election implements Serializable {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;

    private ElectionType electionType;

    private List<ElectionList> listElectionList;

    private Boolean isActive;

    private String electionName;

    private List<Constituency> constituencies;

    public Election() {
    }


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
        this.listElectionList = electionList;
        this.electionName = electionName;
    }

    public Election(long id, LocalDateTime startDate, LocalDateTime finishDate, ElectionType electionType, List<ElectionList> electionList) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.electionType = electionType;
        this.listElectionList = electionList;
    }

    public Election(Long id, LocalDateTime startDate, LocalDateTime finishDate, ElectionType electionType, List<ElectionList> listElectionList, Boolean isActive, String electionName, List<Constituency> constituencies) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.electionType = electionType;
        this.listElectionList = listElectionList;
        this.isActive = isActive;
        this.electionName = electionName;
        this.constituencies = constituencies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public ElectionType getElectionType() {
        return electionType;
    }

    public void setElectionType(ElectionType electionType) {
        this.electionType = electionType;
    }

    public List<ElectionList> getListElectionList() {
        return listElectionList;
    }

    public void setListElectionList(List<ElectionList> listElectionList) {
        this.listElectionList = listElectionList;
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
                ", listElectionList=" + listElectionList +
                ", isActive=" + isActive +
                ", electionName='" + electionName + '\'' +
                ", constituencies=" + constituencies +
                '}';
    }
}

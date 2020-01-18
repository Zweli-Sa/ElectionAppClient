package com.pk.electionappclient.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Election {

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private Boolean isActive;
    private Boolean isFinished;
    private String electionName;

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

    public Election(long id, LocalDateTime startDate, LocalDateTime finishDate, Boolean isActive, Boolean isFinished, String electionName) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.isActive = isActive;
        this.isFinished = isFinished;
        this.electionName = electionName;
    }

    public Election(long id, LocalDateTime startDate, LocalDateTime finishDate, Boolean isActive, Boolean isFinish) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.isActive=isActive;
        this.isFinished = isFinish;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public Boolean getIsFinished() {
        return isFinished;
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

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    @Override
    public String toString() {
        return "Election{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                ", isActive=" + isActive +
                ", electionName='" + electionName + '\'' +
                '}';
    }
}

package com.pk.electionappclient.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Report implements Serializable {

    private Long id;
    private String title;
    private LocalDateTime date;
    private String description;
    private User reporter;

    public Report() {
    }

    public Report(Long id, String title, LocalDateTime date, String description, User reporter) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
        this.reporter = reporter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }
}

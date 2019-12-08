package com.pk.electionappclient.domain;

import java.time.LocalDateTime;

public class Report {

    private long id;

    private String title;

    private LocalDateTime date;

    private String description;

//    private User reporter;

    public Report(long id, String title, LocalDateTime date, String description) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
    }
}

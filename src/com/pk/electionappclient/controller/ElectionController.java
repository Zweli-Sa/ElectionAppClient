package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.Candidate;
import com.pk.electionappclient.domain.Election;
import com.pk.electionappclient.domain.ElectionList;
import com.pk.electionappclient.domain.ElectionType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ElectionController {

    private final static ElectionType presidential = new ElectionType(1, "Prezydenckie");
    private final static ElectionType parliamentary = new ElectionType(2, "Parlamentarne");

    private static List<Election> electionsDB = new ArrayList<>();


    public static List<Election> getElections() {
        return electionsDB;
    }

    public static List<Election> createElectionDay(LocalDateTime startDate, LocalDateTime finishDate, ElectionType electionType, List<ElectionList> list) {
        electionsDB.add(new Election(2l,startDate, finishDate, electionType, list));
        return electionsDB;
    }


}

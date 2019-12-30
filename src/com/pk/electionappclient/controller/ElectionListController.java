package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.Candidate;
import com.pk.electionappclient.domain.ElectionList;

import java.util.ArrayList;
import java.util.List;

public class ElectionListController {

    public static List<ElectionList> electionList = new ArrayList<>();


    public static List<ElectionList> newElectionList(List<Candidate> candidates) {
        electionList.add(new ElectionList(1, candidates));
        return electionList;
    }

}

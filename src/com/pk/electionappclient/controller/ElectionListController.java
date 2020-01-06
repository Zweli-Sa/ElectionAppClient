package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;

import java.util.ArrayList;
import java.util.List;

import static com.pk.electionappclient.controller.AppController.popUpError;
import static com.pk.electionappclient.controller.ClientController.clearCandidateTempList;

public class ElectionListController {

    public static List<ElectionList> electionList = new ArrayList<>();

    public static List<ElectionList> newPresElectionList(int id, List<Candidate> candidates) throws NullPointerException{
        electionList = new ArrayList<>();
        if (!candidates.isEmpty()) {
            id++;
            electionList.add(new ElectionList(id, candidates));
        }
        else {
            popUpError("Dodaj kandydata do listy wyborzcej");
            throw new NullPointerException();
        }
        return electionList;
    }

    public static List<ElectionList> newParlElectionList(int id, List<Candidate> candidates, ElectoralParty electoralParty, Constituency constituency) throws NullPointerException{
        clearCandidateTempList();
        if (!candidates.isEmpty()) {
            id++;
            electionList.add(new ElectionList(id, candidates, electoralParty, constituency));
        }
        else {
            popUpError("Dodaj kandydata do listy wyborzcej");
            throw new NullPointerException();
        }
        return electionList;
    }

    public static void clearElectionList() {
        electionList = new ArrayList<>();
    }


}
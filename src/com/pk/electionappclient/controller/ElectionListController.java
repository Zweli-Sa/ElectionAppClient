package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;

import java.util.ArrayList;
import java.util.List;

import static com.pk.electionappclient.controller.AppController.popUpError;
import static com.pk.electionappclient.controller.ClientController.clearCandidateTempList;

public class ElectionListController {


    public static List<ElectionList> electionList = new ArrayList<>();  //baza ElectionList

    public static List<ElectionList> newPresElectionList(int id, List<Candidate> candidates) throws NullPointerException{
        if (!candidates.isEmpty()) {
            System.out.println("newPresElectionList");
            electionList.add(new ElectionList(id, candidates)); //dodanie obiektu ElectionList do bazy
        }
        else {
            popUpError("Dodaj kandydata do listy wyborzcej");
            throw new NullPointerException();
        }
        return electionList;
    }


    public static List<ElectionList> newParlElectionList(int id, List<Candidate> candidates, ElectoralParty electoralParty, Constituency constituency) throws NullPointerException{
        if (electionList.isEmpty()) {
            electionList.add(new ElectionList(id, candidates, electoralParty, constituency));//dodanie do bazy
        } else {
            if (containPartyElectionlist(constituency, electoralParty)) {
                for (ElectionList el : electionList) {
                    if (constituency.getId() == el.getConstituency().getId() && electoralParty.getId() == el.getElectoralParty().getId()) {
                        System.out.println(electionList.indexOf(el));
                        electionList.get(electionList.indexOf(el)).getCandidates().addAll(candidates);
                    }
                }
            }else {
                electionList.add(new ElectionList(id, candidates, electoralParty, constituency));//dodanie do bazy
            }
        }
        return electionList;
    }



}
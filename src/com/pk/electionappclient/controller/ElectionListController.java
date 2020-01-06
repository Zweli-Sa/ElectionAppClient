package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;

import java.util.ArrayList;
import java.util.List;

import static com.pk.electionappclient.controller.AppController.popUpError;

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
    public static boolean containPartyElectionlist(Constituency constituency, ElectoralParty electoralParty) {
        if(electionList.stream().filter(o -> o.getElectoralParty().getId()==(electoralParty.getId()) && o.getConstituency().getId() == constituency.getId()).findAny().isPresent()) {
            return true;
        }
        return false;
    }

    public static boolean candidateInAnotherConstituency(Constituency constituency, List<Candidate> candidateList) {
        for (Candidate c : candidateList) {
            if(electionList.stream().filter(o -> o.getConstituency().getElection().getId() == constituency.getElection().getId() && o.getCandidates().contains(c)).findAny().isPresent()) {
                return true;
            }
        }
        return false;
    }

    public static List<ElectionList> newParlElectionList(int id, List<Candidate> candidates, ElectoralParty electoralParty, Constituency constituency) throws NullPointerException{
        if (electionList.isEmpty()) {
            electionList.add(new ElectionList(id, candidates, electoralParty, constituency));
        } else {
            if (containPartyElectionlist(constituency, electoralParty)) {
                for (ElectionList el : electionList) {
                    if (constituency.getId() == el.getConstituency().getId() && electoralParty.getId() == el.getElectoralParty().getId()) {
                        System.out.println(electionList.indexOf(el));
                        electionList.get(electionList.indexOf(el)).getCandidates().addAll(candidates);
                    }
                }
            }else {
                electionList.add(new ElectionList(id, candidates, electoralParty, constituency));
            }
        }
        return electionList;
    }
    public static List<ElectionList> getParlElectionListByConstituencyID(Constituency constituency) {
        List<ElectionList> temp = new ArrayList<>();
        for (ElectionList e : electionList) {
            if (e.getConstituency().getId() == constituency.getId()) {
                temp.add(e);
            }
        }
        System.out.println("temp: " + temp);
        return temp;
    }

    public static void clearElectionList() {
        electionList = new ArrayList<>();
    }

    public static void showElectionListDB() {
        System.out.println(electionList);
    }


}
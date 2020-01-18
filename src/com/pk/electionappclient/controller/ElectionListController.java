package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;

import java.util.ArrayList;
import java.util.List;

import static com.pk.electionappclient.controller.AppController.popUpError;
import static com.pk.electionappclient.controller.ClientController.clearCandidateTempList;
import static com.pk.electionappclient.controller.ElectionController.electionsDB;
import static com.pk.electionappclient.controller.ElectionController.getParlElectiosnDB;

public class ElectionListController {

    public static List<ElectionList> electionList = new ArrayList<>();

    public static List<ElectionList> newPresElectionList(int id, List<Candidate> candidates) throws NullPointerException{
        if (!candidates.isEmpty()) {
            System.out.println("newPresElectionList");
            electionList.add(new ElectionList(id, candidates));
        }
        else {
            popUpError("Dodaj kandydata do listy wyborzcej");
            throw new NullPointerException();
        }
        return electionList;
    }
    public static boolean containPartyElectionlist(Constituency constituency, ElectoralParty electoralParty) {
        //getConstituencyListsByElection(election);
        if (electionList.stream().filter(o -> o.getElectoralParty() != null && o.getElectoralParty().getId() == (electoralParty.getId()) && o.getConstituency().getId() != null && o.getConstituency().getId() == constituency.getId()).findAny().isPresent()) {
            return true;
        }
        return false;
    }
//     public static boolean containPartyElectionlist(Constituency constituency, ElectoralParty electoralParty) {
//        if(electionList.stream().filter(o -> o.getElectoralParty().getId()==(electoralParty.getId()) && o.getConstituency().getId() == constituency.getId()).findAny().isPresent()) {
//            return true;
//        }
//        return false;
//    }

    public static boolean candidateInAnotherConstituency(Election election, Constituency constituency, List<Candidate> candidateList) {
        try {
            for (Candidate c : candidateList) {
                if (getCandidatesByElection(election).contains(c)) {
                    return true;
                }
            }
        } catch (NullPointerException e) {

        }
        return false;
    }

    public static List<ElectionList> newParlElectionList(int id, List<Candidate> candidates, ElectoralParty electoralParty, Constituency constituency) throws NullPointerException{
        if (electionList.isEmpty()) {
            electionList.add(new ElectionList(id, candidates, electoralParty, constituency));
        } else {
            if (containPartyElectionlist(constituency, electoralParty)) {
                for (ElectionList el : electionList) {
                    if (el.getElectoralParty() != null && el.getConstituency() != null) {
                        if (constituency.getId() == el.getConstituency().getId() && electoralParty.getId() == el.getElectoralParty().getId()) {
                            System.out.println(electionList.indexOf(el));
                            electionList.get(electionList.indexOf(el)).getCandidates().addAll(candidates);
                        }
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

    public static List<Constituency> getConstituencyListsByElection(Election election) {
        List<Constituency> temp = new ArrayList<>();
        for (Election e : electionsDB) {
            if(e.getId() == election.getId()) {
                temp = e.getConstituencies();
            }
        }
        return temp;
    }

    public static List<Candidate> getCandidatesByElection(Election election) {
        List<Candidate> temp = new ArrayList<>();
        for (Constituency c : getConstituencyListsByElection(election)) {
            for (ElectionList el : c.getElectionLists()) {
                temp.addAll(el.getCandidates());
            }
        }
        return temp;
    }


}
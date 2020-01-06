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
    public static boolean containsPartyElectionlist(Constituency constituency, ElectoralParty electoralParty) {
        if(electionList.stream().filter(o -> o.getElectoralParty().getId()==(electoralParty.getId()) && o.getConstituency().getId() == constituency.getId()).findAny().isPresent()) {
            return true;
        }
        return false;
    }

    public static List<ElectionList> newParlElectionList(int id, List<Candidate> candidates, ElectoralParty electoralParty, Constituency constituency) throws NullPointerException{
        if (electionList.isEmpty()) {
            electionList.add(new ElectionList(id, candidates, electoralParty, constituency));
        } else {
            if (containsPartyElectionlist(constituency, electoralParty)) {
                electionList.removeIf(el -> (el.getElectoralParty().getId()==electoralParty.getId()) && (el.getConstituency().getId() == constituency.getId()));
                electionList.add(new ElectionList(id, candidates, electoralParty, constituency));
//                for (ElectionList el : electionList) {
//                    if (constituency.getId() == el.getConstituency().getId() && electoralParty.getId() == el.getElectoralParty().getId()) {
//                        el = new ElectionList(id, candidates, electoralParty, constituency);
//                    }
//                }
            }else {
                electionList.add(new ElectionList(id, candidates, electoralParty, constituency));
            }
        }
        return electionList;
    }
//    public static List<ElectionList> newParlElectionList(int id, List<Candidate> candidates, ElectoralParty electoralParty, Constituency constituency) throws NullPointerException{
//        List<ElectionList> temp = new ArrayList<>();
//        if (!candidates.isEmpty()) {
//            System.out.println("Jestem: #1");
//            if(!electionList.isEmpty()) {
//                for (ElectionList e : electionList) {
//                    System.out.println("Jestem #2");
//                    if (e.getElectoralParty().getId() == electoralParty.getId() && e.getConstituency().getId() == constituency.getId()) {
//                        System.out.println("Jestem #3");
//                        e = new ElectionList(id, candidates, electoralParty, constituency);
//                    } else {
//                        System.out.println("Jestem #4");
//                        electionList.add(new ElectionList(id, candidates, electoralParty, constituency));
//                    }
//                }
//            } else {
//                electionList.add(new ElectionList(id, candidates, electoralParty, constituency));
//            }
//        }
//        else {
//            popUpError("Dodaj kandydata do listy wyborzcej");
//        }
//        return electionList;
//    } ostatnia proba


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
//    public static boolean candidateExistOnAnotherElectionList(List<Candidate> candidateList, Election election) {
//        for (ElectionList e : electionList) {
//            if (e.getId() == election.getId()) {
//                for (Candidate c : e.getCandidates()) {
//                    if (candidateList.contains(c)) {
//                        return true;
//                    }
//                }
//            }
//            }
//        return false;
//    }

    public static void clearElectionList() {
        electionList = new ArrayList<>();
    }

    public static void showElectionListDB() {
        System.out.println(electionList);
    }


}
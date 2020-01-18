package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.util.ArrayList;
import java.util.List;

import static com.pk.electionappclient.Main.globalID;
import static com.pk.electionappclient.controller.AppController.popUpError;
import static com.pk.electionappclient.controller.ClientController.clearCandidateTempList;
import static com.pk.electionappclient.controller.ElectionController.electionsDB;
import static com.pk.electionappclient.controller.ElectionController.getParlElectiosnDB;

public class ElectionListController {

    public static List<ElectionList> electionList = new ArrayList<>();

    public static ElectionList newPresElectionList(long id, List<Candidate> candidates) throws NullPointerException{
        ElectionList newElectionList = null;
        if (!candidates.isEmpty()) {
            System.out.println("newPresElectionList");
            newElectionList = new ElectionList(id, candidates);
            electionList.add(newElectionList);
        }
        else {
            popUpError("Dodaj kandydata do listy wyborzcej");
            throw new NullPointerException();
        }
        return newElectionList;
    }
    public static boolean containPartyElectionlist(Constituency constituency, ElectoralParty electoralParty) {
        //getConstituencyListsByElection(election);
        List<ElectionList> tempEL = electionListWithConstituencies();
        if (tempEL.stream().filter(o -> o.getElectoralParty().getId() == (electoralParty.getId()) && o.getConstituency().getId() == constituency.getId()).findAny().isPresent()) {
            return true;
        }
        return false;
    }

    public static boolean candidateInAnotherConstituency(List<ElectionList> electionlist, Election election, Constituency constituency, List<Candidate> candidateList) {
        for (Candidate c : candidateList) {
            if(electionlist.stream().filter(o -> o.getConstituency().getElection().getId() == constituency.getElection().getId() && o.getCandidates().contains(c)).findAny().isPresent()) {
                return true;
            }
        }
        return false;
    }

    public static List<ElectionList> newParlElectionList(long id, List<Candidate> candidates, ElectoralParty electoralParty, Constituency constituency) throws NullPointerException {
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


    public static List<ElectionList> electionListWithConstituencies() {
        List<ElectionList> temp = new ArrayList<>();
        for (ElectionList el : electionList){
            if (el.getConstituency() != null) {
                temp.add(el);
            }
        }
        return temp;
    }


}
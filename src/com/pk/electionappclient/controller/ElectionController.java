package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.pk.electionappclient.controller.ClientController.*;
import static com.pk.electionappclient.controller.ConstituencyController.constituenciesDB;
import static com.pk.electionappclient.controller.ElectionListController.electionList;
import static com.pk.electionappclient.controller.ElectionListController.electionListWithConstituencies;

public class ElectionController {

    private final static ElectionType presidential = new ElectionType(1, "Prezydenckie");
    private final static ElectionType parliamentary = new ElectionType(2, "Parlamentarne");

    public static List<Election> electionsDB = new ArrayList<>();
    private static List<Election> inActiveElectionsDB = new ArrayList<>();
    private static List<Election> activeElectionsDB = new ArrayList<>();
    public static List<Election> finishedElectionsDB = new ArrayList<>();
    public static List<Election> presElectionsDB = new ArrayList<>();


    public static List<Election> getParlElectiosnDB() {
        List<Election> temp = new ArrayList<>();
        temp = electionsDB.stream().filter(e -> e.getConstituencies() !=null).collect(Collectors.toList());
        return  temp;
    }

    public static List<Election> getElections() {
        return electionsDB;
    }




    public static List<Election> createElectionDay(long id, LocalDateTime startDate, LocalDateTime finishDate, ElectionType electionType, ElectionList list, Boolean isActive, Boolean isFinished, String name) {
        candidateTempList = new ArrayList<>();
        if(!startDate.equals(null) || !finishDate.equals(null) || !electionType.equals(null) || !list.equals(null)) {
            electionsDB.add(new Election(id, startDate, finishDate, electionType, list, isActive, isFinished, name));
        }
        return electionsDB;
    }

    public static List<Election> getInActiveElections() {
        inActiveElectionsDB = new ArrayList<>();
        for (Election e : electionsDB) {
            if (!e.getIsActive()) {
                inActiveElectionsDB.add(e);
            }
        }
        return inActiveElectionsDB;
    }


    public static List<Election> getActiveElections() {
        activeElectionsDB = new ArrayList<>();
        for (Election e : electionsDB) {
            if (e.getIsActive()) {
                if (!e.getIsFinished()) {
                    activeElectionsDB.add(e);
                }
            }
        }
        return activeElectionsDB;
    }

    public static List<Election> getFinishedElections() {
        finishedElectionsDB = new ArrayList<>();
        for (Election e : electionsDB) {
            if (e.getIsFinished()) {
                finishedElectionsDB.add(e);
            }
        }
        return finishedElectionsDB;
    }

    public static void removeInactiveElection(Election election) {
        electionsDB.removeIf(e -> (e.getId() == election.getId()));
    }

    public static List<Constituency> getCurrentConstituency(Election election) {
        for (Election e : electionsDB) {
            if (e.getId() == election.getId()) {
                return e.getConstituencies();
            }
        }
        return null;
    }

    public static void electionSetConstituency(Election election, List<Constituency> list) {
        for (Election e : electionsDB) {
            if (e.getId() == election.getId()) {
                e.setConstituencies(list);
            }
            }
        }


    public static void show() {
        for (Election e : electionsDB) {
            System.out.println(e);
        }
    }

    public static void initElectionsDB() {
        electionsDB = new ArrayList<>();
  }

    public static List<Candidate> getCandidatesByElection(Election election, Constituency constituency, ElectoralParty electoralParty) {
        List<Candidate> candidates = new ArrayList<>();
        try {
            for (Constituency c : constituenciesDB) {
                if (c.getId() == constituency.getId()) {
                    for (ElectionList cc : c.getElectionLists()) {
                        if (cc.getElectoralParty().getId() == electoralParty.getId())
                            candidates = cc.getCandidates();
                    }
                }
            }
        } catch (NullPointerException e) {
            e.getStackTrace();
        }
        return candidates;
    }

    public static List<ElectionList> getElectionIdByConstituencyID(Constituency constituency) {
        List<ElectionList> elist = new ArrayList<>();
        try {
            for (ElectionList el : electionListWithConstituencies()) {
                if (el.getConstituency().getId() == constituency.getId()) {
                        elist.add(el);
                    }
                }
            } catch (NullPointerException e) {
            e.getStackTrace();
        }
        return elist;
    }

    public static List<Candidate> getCandidatesByPresElection(Election election) {
        List<Candidate> temp = new ArrayList<>();
        for (Candidate c : election.getElectionList().getCandidates()) {
            temp.add(c);
        }
     return temp;

    }

}

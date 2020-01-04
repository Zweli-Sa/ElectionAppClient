package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.pk.electionappclient.controller.ClientController.candidateFinalList;
import static com.pk.electionappclient.controller.ClientController.candidateTempList;

import static com.pk.electionappclient.controller.ConstituencyController.constituenciesDB;
import static com.pk.electionappclient.controller.ConstituencyController.getConstituenciesDB;

public class ElectionController {

    private final static ElectionType presidential = new ElectionType(1, "Prezydenckie");
    private final static ElectionType parliamentary = new ElectionType(2, "Parlamentarne");

    private static List<Election> electionsDB = new ArrayList<>();
    private static List<Election> inActiveElectionsDB = new ArrayList<>();
    private static List<Election> activeElectionsDB = new ArrayList<>();


    public static List<Election> getElections() {
        return electionsDB;
    }


    public static void clearInactiveElectionList() {
        inActiveElectionsDB.clear();
    }

    public static List<Election> createElectionDay(int id, LocalDateTime startDate, LocalDateTime finishDate, ElectionType electionType, List<ElectionList> list) {
        candidateFinalList = new ArrayList<>();
        candidateTempList = new ArrayList<>();
        if(!startDate.equals(null) || !finishDate.equals(null) || !electionType.equals(null) || !list.equals(null)) {
            electionsDB.add(new Election(id, startDate, finishDate, electionType, list));
        }
        return electionsDB;
    }

    public static List<Election> createElectionDayTest(int id, LocalDateTime startDate, LocalDateTime finishDate, ElectionType electionType, List<ElectionList> list, Boolean isActive, String name) {
        candidateFinalList = new ArrayList<>();
        candidateTempList = new ArrayList<>();
        if(!startDate.equals(null) || !finishDate.equals(null) || !electionType.equals(null) || !list.equals(null)) {
            electionsDB.add(new Election(id, startDate, finishDate, electionType, list, isActive, name));
        }
        return electionsDB;
    }

    public static List<Election> getInActiveElections() {
        inActiveElectionsDB = new ArrayList<>();
        for (Election e : electionsDB) {
            if (!e.getActive()) {
                inActiveElectionsDB.add(e);
            }
        }
        return inActiveElectionsDB;
    }

    public static List<Election> getActiveElections() {
        activeElectionsDB = new ArrayList<>();
        for (Election e : electionsDB) {
            if (e.getActive()) {
                activeElectionsDB.add(e);
            }
        }
        return activeElectionsDB;
    }

//    public static void removeInactiveElection(Election el) {
//        System.out.println(inActiveElectionsDB);
//        inActiveElectionsDB.removeIf(e -> (e.getId() == el.getId()) );
//        System.out.println(inActiveElectionsDB);
//    }

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


//    public static void setConstituencyElectionController(Election election, List<Constituency> list) {
//        for (Election e : electionsDB) {
//            if (e.getId() == election.getId()) {
//                System.out.println("Jest : " + e);
//                System.out.println("list:" + list);
//                //e.setConstituencies(list);
//            }
//        }
//    }

    public static void setConstituencyElectionController(Election election) {
        List<Constituency> templist = new ArrayList<>();
        for (Constituency c: constituenciesDB) {
            if (c.getElection().getId() == election.getId()) {
                templist.add(c);
            }
        }
        election.setConstituencies(templist);
    }

    public static void show() {
        for (Election e : electionsDB) {
            System.out.println(e);
        }
    }

    public static void initElectionsDB() {
        electionsDB = new ArrayList<>();
        electionsDB.add(new Election(1l, null, null, parliamentary, null, false, "Wybory parlamentarne 2012"));
        electionsDB.add(new Election(4l, null, null, parliamentary, null, false, "Wybory parlamentarne 2012"));
        electionsDB.add(new Election(5l, null, null, parliamentary, null, false, "Wybory parlamentarne 2012"));
        electionsDB.add(new Election(2l, null, null, parliamentary, null, true, "Wybory parlamentaren 2014"));
    }


}

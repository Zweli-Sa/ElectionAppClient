package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.pk.electionappclient.controller.ClientController.*;
import static com.pk.electionappclient.controller.ConstituencyController.constituenciesDB;
import static com.pk.electionappclient.controller.ElectionListController.electionList;

public class ElectionController {

    private final static ElectionType presidential = new ElectionType(1, "Prezydenckie");
    private final static ElectionType parliamentary = new ElectionType(2, "Parlamentarne");

    private static List<Election> electionsDB = new ArrayList<>(); // baza Election
    private static List<Election> inActiveElectionsDB = new ArrayList<>();
    private static List<Election> activeElectionsDB = new ArrayList<>();
    public static List<Election> finishedElectionsDB = new ArrayList<>();


    public static List<Election> getElections() {
        return electionsDB;
    } // zwraca baze Election



    public static List<Election> createElectionDay(int id, LocalDateTime startDate, LocalDateTime finishDate, ElectionType electionType, List<ElectionList> list, Boolean isActive, Boolean isFinish) {
        clearCandidateTempList();
        if(!startDate.equals(null) || !finishDate.equals(null) || !electionType.equals(null) || !list.equals(null)) {
            electionsDB.add(new Election(id, startDate, finishDate, electionType, list, isActive, isFinish)); //Dodanie do bazy Election
        }
        return electionsDB;
    }

    public static List<Election> createElectionDayTest(int id, LocalDateTime startDate, LocalDateTime finishDate, ElectionType electionType, List<ElectionList> list, Boolean isActive, Boolean isFinished, String name) {
        candidateFinalList = new ArrayList<>();
        candidateTempList = new ArrayList<>();
        if(!startDate.equals(null) || !finishDate.equals(null) || !electionType.equals(null) || !list.equals(null)) {
            electionsDB.add(new Election(id, startDate, finishDate, electionType, list, isActive, isFinished, name)); //Dodanie do bazy Election
        }
        return electionsDB;
    }

    public static void electionSetConstituency(Election election, List<Constituency> list) {
        for (Election e : electionsDB) {
            if (e.getId() == election.getId()) {
                e.setConstituencies(list);//ustawienie okregu wyborczego na obiekcie Election
            }
            }
        }


}

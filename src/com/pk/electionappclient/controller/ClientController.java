package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;
import com.pk.electionappclient.httpresponser.HttpResponser;
import com.pk.electionappclient.mapper.JsonMapper;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.pk.electionappclient.Main.globalID;
import static com.pk.electionappclient.controller.AppController.popUpError;
import static com.pk.electionappclient.controller.ConstituencyController.krakow;
import static com.pk.electionappclient.controller.ConstituencyController.warszawa;
import static com.pk.electionappclient.controller.ElectionController.*;

public class ClientController {

    private static List<User> usersDB = new ArrayList<>();


    private static ElectoralParty sld = new ElectoralParty(3, "Sojusz Lewicy Demokratycznej");
    private static ElectoralParty pis = new ElectoralParty(1, "Prawo i Sprawiedliwość");
    private static ElectoralParty none = new ElectoralParty(4, "Bezpartyjny");
    private static ElectoralParty po = new ElectoralParty(2, "Platforma Obywatelska");


    private static List<Candidate> list;
    public static List<Candidate> candidateTempList = new ArrayList<>();
    private static List<ElectoralParty> electoralParties = new ArrayList<>();


    public static List<User> getUsersDB() {
        return usersDB;
    }

    public static List<User> initUserList() {
        usersDB = new ArrayList<>();
        usersDB.add(new User(1L, warszawa, "user", false,"112233"));
        usersDB.add(new User(2L, krakow, "admin", true, "222222"));
        usersDB.add(new User(3L, krakow, "user", false, "333333"));
        usersDB.add(new User(4L, krakow, "user", false, "444444"));
        usersDB.add(new User(5L, krakow, "user", false, "555555"));

        return usersDB;
    }

    public static List<Candidate> getCandidatesByParty(ElectoralParty party) {
        List<Candidate> temp = new ArrayList<>();
        temp = list.stream().filter(o -> o.getElectoralParty().getId() == (party.getId()))
                .collect(Collectors.toList());
        System.out.println(temp);
        return temp;
    }

    //--------------------votepanel-----------------------------------------------------------------------------------------
    public static Constituency getConstituencyListByUserCityId(Election election, int userCityId) {
        Constituency temp = null;
        for (Constituency c : election.getConstituencies()) {
            for (City city : c.getCityList()) {
                if (city.getId() == userCityId) {
                    temp = c;
                }
            }
        }
        return temp;

    }

    public static List<ElectionList> getElectionListByConstituency(Constituency constituency) {
        List<ElectionList> electionLists = new ArrayList<>();
        electionLists = constituency.getElectionLists();
        return electionLists;
    }

    public static List<ElectoralParty> getElectoralPartiesByElectionList(List<ElectionList> electionListList) {
        List<ElectoralParty> temp = new ArrayList<>();
        for (ElectionList e : electionListList) {
            temp.add(e.getElectoralParty());
        }
        return temp;
    }

    public static List<Candidate> getCandidateByElectionListElectoralParty(List<ElectionList> electionList, ElectoralParty electoralParty) {
        List<Candidate> temp = new ArrayList<>();
        for (ElectionList e : electionList) {
            if (e.getElectoralParty().getId() == electoralParty.getId())
                temp = e.getCandidates();
        }
        return temp;
    }

// ---------------------------------------------------------------------------------------------------------------------

//-----VOTE-Results-----------------------------------------------------------------------------------------------------


    // ---------------------------------------------------------------------------------------------------------------------
    public static List<Candidate> getCandidates() {
        return list;
    }


    public static List<Candidate> addCandidate(String name, String lastName, Education education, String placeOfResidence, ElectoralParty electoralParty) {
        list.add(new Candidate(globalID++, name, lastName, education, placeOfResidence, electoralParty));
        return list;
    }

    public static List<Candidate> removeCadidateFromList(Candidate candidate) {
        list.remove(candidate);
        return list;
    }


    public static List<Candidate> getTempCandidateList() {
        return candidateTempList;
    }


    public static List<Candidate> addCandidateToTempList(Candidate candidate) {
        if (!candidateTempList.contains(candidate)) {
            candidateTempList.add(candidate);
        } else {
            popUpError("Kandydat jest już na liście");
        }
        return candidateTempList;
    }

    public static void clearCandidateTempList() {
        candidateTempList = new ArrayList<>();
    }


    public static List<Candidate> initCandidateList() {
        list = new ArrayList<>();
        list.add(new Candidate(1L, "Adam", "Nowak", Education.MAGISTER, "Kraków", sld));
        list.add(new Candidate(2L, "Jan", "Kowalski", Education.PODSTAWOWE, "Kraków", none));
        list.add(new Candidate(3L, "Jaroslaw", "Kaczynski", Education.ŚREDNIE, "Warszawa", pis));
        list.add(new Candidate(4L, "Antoni", "Nowak", Education.ŚREDNIE, "Warszawa",sld));
        list.add(new Candidate(5L, "Andrzej", "Duda", Education.ŚREDNIE, "Warszawa", none));
        list.add(new Candidate(6L, "Zbigniew", "Stonoga", Education.ŚREDNIE, "Warszawa", pis));
        list.add(new Candidate(7L, "Jakub", "Kowalski", Education.ŚREDNIE, "Berlin",sld));
        list.add(new Candidate(8L, "Marcin", "Kowalczyk", Education.ŚREDNIE, "Warszawa", none));
        list.add(new Candidate(9L, "Tomasz", "Tusk", Education.ŚREDNIE, "Warszawa", pis));

        return list;
    }

    public static List<ElectoralParty> getPartyDB() {
        return electoralParties;
    }

    public static void addElectoralPartyToDatabase(ElectoralParty electoralParty) {
        if (electoralParties.stream().filter(e -> e.getName().equals(electoralParty.getName())).findFirst().isPresent()) {
            popUpError("Partia o takiej nazwie juz istnieje!");
        } else {
            electoralParties.add(electoralParty);
        }
    }

    public static void deleteElectoralParty(ElectoralParty electoralParty) {
        electoralParties.remove(electoralParty);
    }

    public static List<ElectoralParty> getPartyByConstituency(Constituency constituency) {
        List<ElectoralParty> electoralParties = new ArrayList<>();
        for (Election e : finishedElectionsDB) {
            System.out.println("Election e: " + e);
            for (Constituency c : e.getConstituencies()) {
                if (c.getId() == constituency.getId()) {
                    for (ElectionList el : c.getElectionLists()) {
                        System.out.println("ElectionList el:" + el);
                        if (el.getConstituency().getId() == constituency.getId()) {
                            electoralParties.add(el.getElectoralParty());
                        }
                    }

                }
            }
        }
        return electoralParties;
    }


    public static List<ElectoralParty> initPartiesList() {
        electoralParties.add(sld);
        electoralParties.add(po);
        electoralParties.add(pis);
        electoralParties.add(none);
        return electoralParties;
    }

    public static List<Candidate> getPresCandidates(Election election) {
        ElectionList elist = election.getElectionList();
        List<Candidate> temp = elist.getCandidates();
        return temp;

    }

}

package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;
import com.pk.electionappclient.httpresponser.HttpResponser;
import com.pk.electionappclient.mapper.JsonMapper;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.pk.electionappclient.controller.AppController.popUpError;

import static com.pk.electionappclient.controller.ElectionController.getElections;

public class ClientController {

    public static int id = 0;


    private static ElectoralParty sld = new ElectoralParty(3, "Sojusz Lewicy Demokratycznej");
    private static ElectoralParty pis = new ElectoralParty(1, "Prawo i Sprawiedliwość");
    private static ElectoralParty none = new ElectoralParty(4, "Bezpartyjny");
    private static ElectoralParty po = new ElectoralParty(2, "Platforma Obywatelska");




    private static List<Candidate> list;
    public static List<Candidate> candidateTempList = new ArrayList<>();
    public static List<Candidate> candidateFinalList = new ArrayList<>();
    private static List<ElectoralParty> electoralParties = new ArrayList<>();
//    public static List<City> citiesDB = new ArrayList<>();
//    public static List<City> citiesTempList = new ArrayList<>();

    public static void createCandidate(Candidate candidate) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(candidate);

    }

    public static List<Candidate> getCandidatesByParty(ElectoralParty party) {
        List<Candidate> temp = new ArrayList<>();
        temp = list.stream().filter(o -> o.getElectoralParty().getId()==(party.getId()))
                .collect(Collectors.toList());
        System.out.println(temp);
        return temp;
    }
//--------------------votepanel-----------------------------------------------------------------------------------------
    public static List<Election> getElectionBySelectedElection(Election election) {
        List<Election> temp = new ArrayList<>();
        temp = getElections().stream().filter(o -> o.getId() == election.getId())
                .collect(Collectors.toList());
        return temp;
    }

    public static Election getElectionByElection(Election election) {
        Election temp = null;
        for (Election e : getElections()) {
            if (election.getId() == e.getId()) {
                temp = e;
            }
        }
        return temp;
    }


    public static Constituency getConstituencyListByUserCityId(Election election, int userId) {
        Election e = getElectionByElection(election);
        Constituency temp = null;
        for (Constituency c : e.getConstituencies()) {
            for (City city : c.getCityList()) {
                if (city.getId() == userId) {
                    temp = c;
                }
            }
        }
        return temp;

    }

    public static List<ElectionList> getElectionListByConstituency(Constituency constituency) {
        constituency.getElectionLists();
        List<ElectionList> electionLists = new ArrayList<>();
        electionLists = constituency.getElectionLists();
        return electionLists;
    }

    public static List<ElectoralParty> getElectoralPartiesByElectionList(List<ElectionList> electionListList) {
        List<ElectoralParty> temp = new ArrayList<>();
        for (ElectionList e : electionListList) {
            temp.add(e.getElectoralParty());
        }
        System.out.println("ElectoralParty temp: "+temp);
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


    public static List<Candidate> setCandidateFinalList() {
        candidateFinalList.addAll(candidateTempList);
        return candidateFinalList;
    }

    public static List<Candidate> addCandidate(String name, String lastName, Education education, String placeOfResidence, ElectoralParty electoralParty) {
        list.add(new Candidate(1l, name, lastName, education,placeOfResidence, electoralParty));
        return list;
    }

    public static List<Candidate> removeCadidateFromList(Candidate candidate) {
        list.remove(candidate);
        return list;
    }


    public static List<Candidate> getTempCandidateList() {
        return candidateTempList;
    }
    public static List<Candidate> getCandidateFinalList() {
        return candidateFinalList;
    }


    public static List<Candidate> addCandidateToTempList(Candidate candidate) {
        if (!candidateTempList.contains(candidate)) {
            candidateTempList.add(candidate);
        } else{
            popUpError("Kandydat jest już na liście");
        }
        return candidateTempList;
    }

    public static void clearCandidateTempList() {
        candidateTempList = new ArrayList<>();
    }



    public static User getUser(Long id) throws IOException {
        return JsonMapper.mapToObject(HttpResponser.get("/v1/user/getUser/" + id));
    }

    public static boolean checkLoginData() {
        return true;
    }

    public static List<Report> getUserReports(Long id) {
        return new ArrayList<>();
    }

    public static List<VoteResult> getVoteResults() {
        return new ArrayList<>();
    }

    public static List<VoteResult> getVoteResultByElection(Election election) {
        return new ArrayList<>();
    }

    public static List<Candidate> initCandidateList() {
        list = new ArrayList<>();
        list.add(new Candidate(22222l, "Adam", "Nowak",Education.MAGISTER, "Kraków", sld));
        list.add(new Candidate(33333l, "Jan", "Kowalski",Education.PODSTAWOWE, "Kraków", none));
        list.add(new Candidate(44444l, "Jaroslaw", "Kaczynski", Education.ŚREDNIE, "Warszawa", pis));

        return list;
    }

    public static List<ElectoralParty> getPartyDB() {
        return electoralParties;
    }



    public static List<ElectoralParty> initPartiesList() {
        electoralParties.add(sld);
        electoralParties.add(po);
        electoralParties.add(pis);
        electoralParties.add(none);
        return electoralParties;
    }

}

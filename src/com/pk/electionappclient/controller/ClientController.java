package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;
import com.pk.electionappclient.httpresponser.HttpResponser;
import com.pk.electionappclient.mapper.JsonMapper;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class ClientController {


    private static ElectoralParty sld = new ElectoralParty(3, "Sojusz Lewicy Demokratycznej");
    private static ElectoralParty pis = new ElectoralParty(1, "Prawo i Sprawiedliwość");
    private static ElectoralParty none = new ElectoralParty(1, "Bezpartyjny");
    private static ElectoralParty po = new ElectoralParty(2, "Platforma Obywatelska");

    private static List<Candidate> list;

    private static List<ElectoralParty> electoralParties = new ArrayList<>();



    public static void createCandidate(Candidate candidate) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(candidate);

    }

    public static List<Candidate> getCandidates() {
        return list;
    }

    public static List<Candidate> addCandidate(String name, String lastName, Education education, String placeOfResidence, ElectoralParty electoralParty) {
        list.add(new Candidate(1l, name, lastName, education,placeOfResidence, electoralParty));
        return list;
    }

    public static List<Candidate> removeCadidateFromList(Candidate candidate) {
        list.remove(candidate);
        return list;
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
        list.add(new Candidate(12l, "Adam", "Nowak",Education.MAGISTER, "Kraków", sld));
        list.add(new Candidate(12l, "Jan", "Kowalski",Education.PODSTAWOWE, "Kraków", none));
        list.add(new Candidate(1l, "Jaroslaw", "Kaczynski", Education.ŚREDNIE, "Warszawa", pis));
        return list;
    }

    public static List<ElectoralParty> initPartiesList() {
        electoralParties.add(sld);
        electoralParties.add(po);
        electoralParties.add(pis);
        electoralParties.add(none);
        return electoralParties;
    }
}

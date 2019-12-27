package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;
import com.pk.electionappclient.httpresponser.HttpResponser;
import com.pk.electionappclient.mapper.JsonMapper;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientController {


    private static ElectoralParty sld = new ElectoralParty(3, "Sojusz Lewicy Demokratycznej");
    private static ElectoralParty pis = new ElectoralParty(1, "Prawo i Sprawiedliwość");
    private static ElectoralParty none = new ElectoralParty(1, "Bezpartyjny");
    private static ElectoralParty po = new ElectoralParty(2, "Platforma Obywatelska");

    private static List<Candidate> list = new ArrayList<>();



    public static void createCandidate(Candidate candidate) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(candidate);

    }

    public static List<Candidate> getCandidates() {
        return list;
    }

    public static List<Candidate> postCandidates(String name, String lastName, String education, String placeOfResidence) {
        list.add(new Candidate(1l, name, lastName, education,placeOfResidence, pis));
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
        list.add(new Candidate(12l, "Adam", "Nowak","Podstawowe", "Kraków", sld));
        list.add(new Candidate(12l, "Jan", "Kowalski","Wyższe", "Kraków", none));
        list.add(new Candidate(1l, "Jaroslaw", "Kaczynski", "Wyższe", "Warszawa", pis));
        return list;
    }
}

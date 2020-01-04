package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;
import com.pk.electionappclient.httpresponser.HttpResponser;
import com.pk.electionappclient.mapper.JsonMapper;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.*;

import static com.pk.electionappclient.controller.AppController.popUpError;

public class ClientController {

    public static int id = 0;


    private static ElectoralParty sld = new ElectoralParty(3, "Sojusz Lewicy Demokratycznej");
    private static ElectoralParty pis = new ElectoralParty(1, "Prawo i Sprawiedliwość");
    private static ElectoralParty none = new ElectoralParty(1, "Bezpartyjny");
    private static ElectoralParty po = new ElectoralParty(2, "Platforma Obywatelska");

    private static City krakow = new City(10l, "Kraków");
    private static City warszawa = new City(20l, "Warszawa");
    private static City wroclaw = new City(30l, "Wroclaw");


    private static List<Candidate> list;
    public static List<Candidate> candidateTempList = new ArrayList<>();
    public static List<Candidate> candidateFinalList = new ArrayList<>();
    private static List<ElectoralParty> electoralParties = new ArrayList<>();
    private static List<City> citiesDB = new ArrayList<>();
    public static List<City> citiesTempList = new ArrayList<>();

    public static void createCandidate(Candidate candidate) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(candidate);

    }


    public static List<City> getCitiesDB() {
        return citiesDB;
    }

    public static List<City> getCitiesTempList() {
        return citiesTempList;
    }
    public static List<City> addCityToTempList(City city) {
      //  citiesTempList = new ArrayList<>();
        if (!citiesTempList.contains(city)) {
            citiesTempList.add(city);
        } else{
            popUpError("Miasto jest już na liście");
        }
        return citiesTempList;
    }

    public static List<City> clearCityTempList() {
        citiesTempList.clear();
        return citiesTempList;
    }

    public static List<City> removeCityTempList(City city) {
        citiesTempList.remove(city);
        return citiesTempList;
    }






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
        candidateTempList = new ArrayList<>();
        if (!candidateTempList.contains(candidate)) {
            candidateTempList.add(candidate);
        } else{
            popUpError("Kandydat jest już na liście");
        }
        return candidateTempList;
    }

    public static List<Candidate> clearCandidateTempList() {
        candidateTempList.clear();
        return candidateTempList;
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


    public static List<ElectoralParty> initPartiesList() {
        electoralParties.add(sld);
        electoralParties.add(po);
        electoralParties.add(pis);
        electoralParties.add(none);
        return electoralParties;
    }

    public static void initCityDB() {
        citiesDB.add(krakow);
        citiesDB.add(warszawa);
        citiesDB.add(wroclaw);
    }
}

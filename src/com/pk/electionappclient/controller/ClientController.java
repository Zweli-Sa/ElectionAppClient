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
import static com.pk.electionappclient.controller.ElectionController.*;

public class ClientController {

    public static int id = 0;

    private static List<Candidate> list; // baza Candidates
    public static List<Candidate> candidateTempList = new ArrayList<>();
    public static List<Candidate> candidateFinalList = new ArrayList<>();
    private static List<ElectoralParty> electoralParties = new ArrayList<>(); //Baza ElectoralParty
//    public static List<City> citiesDB = new ArrayList<>();
//    public static List<City> citiesTempList = new ArrayList<>();


// ---------------------------------------------------------------------------------------------------------------------

//-----VOTE-Results-----------------------------------------------------------------------------------------------------





// ---------------------------------------------------------------------------------------------------------------------


    public static List<Candidate> addCandidate(String name, String lastName, Education education, String placeOfResidence, ElectoralParty electoralParty) {
        list.add(new Candidate(1l, name, lastName, education,placeOfResidence, electoralParty)); // dodanie kandydata do bazy Candidate
        return list;
    }

    public static List<Candidate> removeCadidateFromList(Candidate candidate) {// usniecie kandydata z bazy Candidate
        return list;
        list.remove(candidate);
        return list;
    }



    public static List<ElectoralParty> getPartyDB() {
        return electoralParties;
    } // zwraca Baze ElectoralParty



}

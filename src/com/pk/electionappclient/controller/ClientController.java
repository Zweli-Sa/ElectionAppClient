package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;
import com.pk.electionappclient.httpresponser.HttpResponser;
import com.pk.electionappclient.mapper.JsonMapper;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.pk.electionappclient.controller.AppController.popUpError;
import static com.pk.electionappclient.controller.ElectionController.*;

public class ClientController {

    public static int id = 0;


    private static ElectoralParty sld = new ElectoralParty(3, "Sojusz Lewicy Demokratycznej");
    private static ElectoralParty pis = new ElectoralParty(1, "Prawo i Sprawiedliwość");
    private static ElectoralParty none = new ElectoralParty(4, "Bezpartyjny");
    private static ElectoralParty po = new ElectoralParty(2, "Platforma Obywatelska");




    private static List<Candidate> list;
    public static List<Candidate> candidateTempList = new ArrayList<>();
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



    public static Constituency getConstituencyListByUserCityId(Election election, int userId) {
        Constituency temp = null;
        for (Constituency c : election.getConstituencies()) {
            for (City city : c.getCityList()) {
                if (city.getId() == userId) {
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
        RestTemplate restTemplate = new RestTemplate();
        List<Candidate> candidateList = new ArrayList<>();
        try {
            Candidate[] candidates = restTemplate.getForObject("http://localhost:8080/v1/election/getCandidates", Candidate[].class);
            candidateList = Arrays.asList(candidates);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return candidateList;
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

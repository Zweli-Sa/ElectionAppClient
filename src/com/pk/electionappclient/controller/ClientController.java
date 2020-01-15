package com.pk.electionappclient.Controller;

import com.pk.electionappclient.domain.Candidate;
import com.pk.electionappclient.domain.Education;
import com.pk.electionappclient.domain.ElectoralParty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

import static com.pk.electionappclient.Controller.AppController.popUpError;

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

    public static void createCandidate(Candidate candidate) {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = null;
        try {
            uri = new URI("http://localhost:8080/v1/election/createCandidate");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");
        headers.set("X-COM-LOCATION", "PL");
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Candidate> request = new HttpEntity<>(candidate,headers);
        restTemplate.postForEntity(uri, request, String.class);

    }

    public static List<Candidate> getCandidatesByParty(ElectoralParty party) {
        List<Candidate> temp = new ArrayList<>();
        temp = list.stream().filter(o -> o.getElectoralParty().getId().equals(party.getId()))
                .collect(Collectors.toList());
        System.out.println(temp);
        return temp;
    }


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
        Candidate candidate = new Candidate(1l, name, lastName, education,placeOfResidence, electoralParty);
        list.add(candidate);
        createCandidate(candidate);

        return list;
    }

    public static List<Candidate> removeCadidateFromList(Candidate candidate) {
        list.remove(candidate);

        Map<String, String> params = new HashMap<String, String>();

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete ("http://localhost:8080/v1/election/deleteCandidate/" + candidate.getId(),  params );
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
        list.add(new Candidate(44444l, "Jaroslaw", "Kaczynski", Education.SREDNIE, "Warszawa", pis));

        return list;
    }

    public static List<ElectoralParty> getPartyDB() {

        RestTemplate restTemplate = new RestTemplate();
        List<ElectoralParty> electoralPartyList = new ArrayList<>();
        try {
            ElectoralParty[] electoralParties = restTemplate.getForObject("http://localhost:8080/v1/election/getElectoralParties", ElectoralParty[].class);
            electoralPartyList = Arrays.asList(electoralParties);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return electoralPartyList;
    }



    public static List<ElectoralParty> initPartiesList() {
        electoralParties.add(sld);
        electoralParties.add(po);
        electoralParties.add(pis);
        electoralParties.add(none);
        return electoralParties;
    }

}

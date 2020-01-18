package com.pk.electionappclient.Controller;

import com.pk.electionappclient.domain.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.pk.electionappclient.Controller.AppController.popUpError;
import static com.pk.electionappclient.Controller.ClientController.getMessageConverters;
import static java.util.Optional.ofNullable;

public class ElectionListController {

    private static final String URL = "http://localhost:8080/v1/election";

    public static List<ElectionList> getElectionLists() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getElectionLists")
                .build().encode().toUri();
        ElectionList[] boardResponse = restTemplate.getForObject(uri, ElectionList[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new ElectionList[0]));
    }

    public static void createElectionList(ElectionList electionList) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI url = UriComponentsBuilder.fromHttpUrl(URL + "/createElectionList")
                .queryParam("id", electionList.getId())
                .queryParam("name", electionList.getName())
                .queryParam("description", electionList.getDescription())
                .queryParam("election", electionList.getElection())
                .queryParam("constituency", electionList.getConstituency())
                .queryParam("candidates", electionList.getCandidates())
                .queryParam("electoralParty", electionList.getElectoralParty()).build().encode().toUri();
        restTemplate.postForObject(url, null, ElectionList.class);
    }

    public static List<ElectionList> newPresElectionList(int id, List<Candidate> candidates) throws NullPointerException{
        if (!candidates.isEmpty()) {
            System.out.println("newPresElectionList");
            ElectionList electionList = new ElectionList(id, candidates);

//            RestTemplate restTemplate = new RestTemplate();
//
//            URI uri = null;
//            try {
//                uri = new URI("http://localhost:8080/v1/election/createElectionList");
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            }
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("X-COM-PERSIST", "true");
//            headers.set("X-COM-LOCATION", "PL");
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<ElectionList> request = new HttpEntity<>(election,headers);
//            restTemplate.postForEntity(uri, request, String.class);

            createElectionList(electionList);
        }
        else {
            popUpError("Dodaj kandydata do listy wyborczej");
            throw new NullPointerException();
        }
        return getElectionLists();
    }
    public static boolean containPartyElectionlist(Constituency constituency, ElectoralParty electoralParty) {
        List<ElectionList> electionLists = getElectionLists();
        if(electionLists.stream().filter(o -> o.getElectoralParty().getId()==(electoralParty.getId()) && o.getConstituency().getId() == constituency.getId()).findAny().isPresent()) {
            return true;
        }
        return false;
    }

    public static boolean candidateInAnotherConstituency(Constituency constituency, List<Candidate> candidateList) {
        List<ElectionList> electionLists = getElectionLists();
        for (Candidate c : candidateList) {
            if(electionLists.stream().filter(o -> o.getConstituency().getElection().getId() == constituency.getElection().getId() && o.getCandidates().contains(c)).findAny().isPresent()) {
                return true;
            }
        }
        return false;
    }

    public static List<ElectionList> newParlElectionList(int id, List<Candidate> candidates, ElectoralParty electoralParty, Constituency constituency) throws NullPointerException{
        List<ElectionList> electionLists = getElectionLists();
        if (electionLists.isEmpty()) {
            ElectionList electionList = new ElectionList(id, candidates, electoralParty, constituency);

//            RestTemplate restTemplate = new RestTemplate();
//
//            URI uri = null;
//            try {
//                uri = new URI("http://localhost:8080/v1/election/createElectionList");
//            } catch (URISyntaxException e) {
//                e.printStackTrace();
//            }
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("X-COM-PERSIST", "true");
//            headers.set("X-COM-LOCATION", "PL");
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            HttpEntity<ElectionList> request = new HttpEntity<>(election,headers);
//            restTemplate.postForEntity(uri, request, String.class);

            createElectionList(electionList);

        } else {
            if (containPartyElectionlist(constituency, electoralParty)) {
                for (ElectionList el : electionLists) {
                    if (constituency.getId() == el.getConstituency().getId() && electoralParty.getId() == el.getElectoralParty().getId()) {
                        System.out.println(electionLists.indexOf(el));
                        electionLists.get(electionLists.indexOf(el)).getCandidates().addAll(candidates);
                    }
                }
            }else {
                ElectionList electionList = new ElectionList(id, candidates, electoralParty, constituency);
                createElectionList(electionList);
//                URI uri = null;
//                try {
//                    uri = new URI("http://localhost:8080/v1/election/createElectionList");
//                } catch (URISyntaxException e) {
//                    e.printStackTrace();
//                }
//                HttpHeaders headers = new HttpHeaders();
//                headers.set("X-COM-PERSIST", "true");
//                headers.set("X-COM-LOCATION", "PL");
//                headers.setContentType(MediaType.APPLICATION_JSON);
//                HttpEntity<ElectionList> request = new HttpEntity<>(election,headers);
//                restTemplate.postForEntity(uri, request, String.class);
            }
        }
        return getElectionLists();
    }
    public static List<ElectionList> getParlElectionListByConstituencyID(Constituency constituency) {
        List<ElectionList> temp = new ArrayList<>();
        List<ElectionList> electionLists = getElectionLists();
        for (ElectionList e : electionLists) {
            if (e.getConstituency().getId() == constituency.getId()) {
                temp.add(e);
            }
        }
        System.out.println("temp: " + temp);
        return temp;
    }

    public static void showElectionListDB() {
        System.out.println(getElectionLists().toString());
    }


}
package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.pk.electionappclient.controller.AppController.popUpError;
import static com.pk.electionappclient.controller.ClientController.clearCandidateTempList;
import static com.pk.electionappclient.controller.ClientController.getMessageConverters;
import static java.util.Optional.ofNullable;

public class ElectionListController {

    private static final String URL = "http://localhost:8080/v1/election";

    public static List<ElectionList> electionList = new ArrayList<>();

    public static List<ElectionList> newPresElectionList(int id, List<Candidate> candidates) throws NullPointerException{
        if (!candidates.isEmpty()) {
            System.out.println("newPresElectionList");
            electionList.add(new ElectionList(id, candidates));
        }
        else {
            popUpError("Dodaj kandydata do listy wyborzcej");
            throw new NullPointerException();
        }
        return electionList;
    }

    public static List<ElectionList> getElectionLists() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getElectionLists")
                .build().encode().toUri();
        ElectionList[] boardResponse = restTemplate.getForObject(uri, ElectionList[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new ElectionList[0]));
    }

    public static boolean containPartyElectionlist(Constituency constituency, ElectoralParty electoralParty) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/containPartyElectionlist/" + constituency.getId() + "/" + electoralParty.getId())
                .build().encode().toUri();
        Boolean boardResponse = restTemplate.getForObject(uri, Boolean.class);
        return ofNullable(boardResponse).orElse(new Boolean(false));
    }

    public static boolean candidateInAnotherConstituency(Constituency constituency, List<Candidate> candidateList) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/candidateInAnotherConstituency/" + constituency.getId())
                .build().encode().toUri();
        Boolean boardResponse = restTemplate.getForObject(uri, Boolean.class);
        return ofNullable(boardResponse).orElse(new Boolean(false));
    }

    public static Long getConstituencyIdByElecionListId(Long electionListId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getConstituencyIdByElecionListId/" + electionListId)
                .build().encode().toUri();
        Constituency boardResponse = restTemplate.getForObject(uri, Constituency.class);
        return ofNullable(boardResponse.getId()).orElse(new Long(-1L));
    }

    public static Long getElectoralPartyIdByElecionListId(Long electoralPartyId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getElectoralPartyIdByElecionListId/" + electoralPartyId)
                .build().encode().toUri();
        Constituency boardResponse = restTemplate.getForObject(uri, Constituency.class);
        return ofNullable(boardResponse.getId()).orElse(new Long(-1L));
    }

    public static List<Candidate> getCandidatesByElectionListId(Long electionListId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getCandidatesByElectionListId/" + electionListId)
                .build().encode().toUri();
        Candidate[] boardResponse = restTemplate.getForObject(uri, Candidate[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new Candidate[0]));
    }

    public static List<ElectionList> newParlElectionList(int id, List<Candidate> candidates, ElectoralParty electoralParty, Constituency constituency) throws NullPointerException{
//        if (electionList.isEmpty()) {
////            electionList.add(new ElectionList(id, candidates, electoralParty, constituency));
////        } else {
////            if (containPartyElectionlist(constituency, electoralParty)) {
////                for (ElectionList el : electionList) {
////                    if (constituency.getId() == el.getConstituency().getId() && electoralParty.getId() == el.getElectoralParty().getId()) {
////                        System.out.println(electionList.indexOf(el));
////                        electionList.get(electionList.indexOf(el)).getCandidates().addAll(candidates);
////                    }
////                }
////            }else {
////                electionList.add(new ElectionList(id, candidates, electoralParty, constituency));
////            }
////        }
////        return electionList;
        return new ArrayList<>();
    }

    public static void clearElectionList() {
        electionList = new ArrayList<>();
    }

    public static void showElectionListDB() {
        System.out.println(electionList);
    }


}
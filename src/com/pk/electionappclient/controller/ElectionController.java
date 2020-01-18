package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.pk.electionappclient.controller.ClientController.*;
import static com.pk.electionappclient.controller.ConstituencyController.constituenciesDB;
import static com.pk.electionappclient.controller.ConstituencyController.getConstituencyByElectionID;
import static com.pk.electionappclient.controller.ElectionListController.electionList;
import static java.util.Optional.ofNullable;

public class ElectionController {

    private static final String URL = "http://localhost:8080/v1/election";

    public static void setElectionTypeInElection(Long electionId, ElectionType electionType) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI url = UriComponentsBuilder.fromHttpUrl(URL + "/setElectionTypeInElection/" + electionId)
                .queryParam("id", electionType.getId())
                .queryParam("name", electionType.getName()).build().encode().toUri();
        restTemplate.postForObject(url, null, ElectionType.class);
    }

    public static List<Election> getElections() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getElections")
                .build().encode().toUri();
        Election[] boardResponse = restTemplate.getForObject(uri, Election[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new Election[0]));
    }

    public static List<Election> createElectionDay(int id, LocalDateTime startDate, LocalDateTime finishDate, ElectionType electionType, List<ElectionList> list, Boolean isActive, Boolean isFinish) {
        clearCandidateTempList();
        if(!startDate.equals(null) || !finishDate.equals(null) || !electionType.equals(null) || !list.equals(null)) {
            //electionsDB.add(new Election(id, startDate, finishDate, electionType, list, isActive, isFinish));
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setMessageConverters(getMessageConverters());
            URI url = UriComponentsBuilder.fromHttpUrl(URL + "/createCity")
                    .queryParam("id", 0L)
                    .queryParam("startDate", startDate)
                    .queryParam("finishDate", finishDate)
                    .queryParam("isActive", isActive)
                    .queryParam("isFinished", isFinish)
                    .queryParam("electionName", "Wybory").build().encode().toUri();
            restTemplate.postForObject(url, null, Election.class);
        }
        return getElections();
    }

    public static List<Election> createElectionDayTest(int id, LocalDateTime startDate, LocalDateTime finishDate, ElectionType electionType, List<ElectionList> list, Boolean isActive, Boolean isFinished, String name) {
        candidateFinalList = new ArrayList<>();
        candidateTempList = new ArrayList<>();
        if(!startDate.equals(null) || !finishDate.equals(null) || !electionType.equals(null) || !list.equals(null)) {
            //electionsDB.add(new Election(id, startDate, finishDate, electionType, list, isActive, isFinished, name));
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setMessageConverters(getMessageConverters());
            URI url = UriComponentsBuilder.fromHttpUrl(URL + "/createCity")
                    .queryParam("id", 0L)
                    .queryParam("startDate", startDate)
                    .queryParam("finishDate", finishDate)
                    .queryParam("isActive", isActive)
                    .queryParam("isFinished", isFinished)
                    .queryParam("electionName", "Wybory").build().encode().toUri();
            restTemplate.postForObject(url, null, Election.class);
        }
        return getElections();
    }

    public static List<Election> getInActiveElections() {
        List<Election> elections = getElections();
        List<Election> inActiveElections = new ArrayList<>();

        for (Election tmp : elections) {
            if (!tmp.getIsActive()) {
                inActiveElections.add(tmp);
            }
        }
        return inActiveElections;
    }


    public static List<Election> getActiveElections() {
        List<Election> elections = getElections();
        List<Election> activeElections = new ArrayList<>();

        for (Election tmp : elections) {
            if (tmp.getIsActive()) {
                activeElections.add(tmp);
            }
        }
        return activeElections;
    }

    public static List<Election> getFinishedElections() {
        List<Election> elections = getElections();
        List<Election> finishedElections = new ArrayList<>();

        for (Election tmp : elections) {
            if (tmp.getFinished()) {
                finishedElections.add(tmp);
            }
        }
        return finishedElections;
    }

    public static void removeInactiveElection(Election election) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/deleteElection/" + election.getId())
                .build().encode().toUri();
        restTemplate.delete(uri);
    }

    public static List<Constituency> getCurrentConstituency(Election election) {
        return getConstituencyByElectionID(election);
    }

    public static void electionSetConstituency(Election election, List<Constituency> list) {
        for (Constituency constituency : list) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setMessageConverters(getMessageConverters());
            URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/electionSetConstituency/" + election.getId() + "/" + constituency.getId())
                    .build().encode().toUri();
            restTemplate.getForObject(uri, Election.class);
        }
    }


    public static void show() {
        for (Election e : getElections()) {
            System.out.println(e);
        }
    }

    public static List<Candidate> getCandidatesElection(Election election, Constituency constituency, ElectoralParty electoralParty) {
        List<Candidate> candidates = new ArrayList<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setMessageConverters(getMessageConverters());
            URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getCandidatesElection/" + constituency.getId() + "/" + electoralParty.getId())
                    .build().encode().toUri();
            Candidate[] boardResponse = restTemplate.getForObject(uri, Candidate[].class);
            candidates =  Arrays.asList(ofNullable(boardResponse).orElse(new Candidate[0]));
        } catch (NullPointerException e) {
            System.out.println("Null w getCandidatesElection");
        }
        return candidates;
    }

    public static List<ElectionList> getElectionListsByConstituencyID(Constituency constituency) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getElectionListsByConstituencyId/" + constituency.getId())
                .build().encode().toUri();
        ElectionList[] boardResponse = restTemplate.getForObject(uri, ElectionList[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new ElectionList[0]));
    }

}

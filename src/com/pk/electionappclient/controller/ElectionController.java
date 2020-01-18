package com.pk.electionappclient.Controller;

import com.pk.electionappclient.domain.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.pk.electionappclient.Controller.ClientController.getMessageConverters;
import static java.util.Optional.ofNullable;

public class ElectionController {

    private static final String URL = "http://localhost:8080/v1/election";

    public static List<Election> getElections() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getElections")
                .build().encode().toUri();
        Election[] boardResponse = restTemplate.getForObject(uri, Election[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new Election[0]));
    }

    public static void createElection(Election election) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI url = UriComponentsBuilder.fromHttpUrl(URL + "/createElection")
                .queryParam("id", election.getId())
                .queryParam("startDate", election.getStartDate())
                .queryParam("finishDate", election.getFinishDate())
                .queryParam("electionType", election.getElectionType())
                .queryParam("listElectionList", election.getListElectionList())
                .queryParam("isActive", election.getActive())
                .queryParam("electionName", election.getElectionName())
                .queryParam("constituencies", election.getConstituencies()).build().encode().toUri();
        restTemplate.postForObject(url, null, Election.class);
    }

    public static List<Election> createElectionDay(int id, LocalDateTime startDate, LocalDateTime finishDate, ElectionType electionType, List<ElectionList> list) {
        if(!startDate.equals(null) || !finishDate.equals(null) || !electionType.equals(null) || !list.equals(null)) {
            Election election = new Election(id, startDate, finishDate, electionType, list);
            createElection(election);
        }
        return getElections();
    }

    public static List<Election> createElectionDayTest(int id, LocalDateTime startDate, LocalDateTime finishDate, ElectionType electionType, List<ElectionList> list, Boolean isActive, String name) {
        if(!startDate.equals(null) || !finishDate.equals(null) || !electionType.equals(null) || !list.equals(null)) {
            Election election = new Election(id, startDate, finishDate, electionType, list, isActive, name);
            createElection(election);
        }
        return getElections();
    }

    public static List<Election> getInActiveElections() {
        List<Election> temp = new ArrayList<>();
        List<Election> elections = getElections();
        for (Election e : elections) {
            if (!e.getActive()) {
                temp.add(e);
            }
        }
        return temp;
    }


    public static List<Election> getActiveElections() {
        List<Election> temp = new ArrayList<>();
        List<Election> elections = getElections();
        for (Election e : elections) {
            if (e.getActive()) {
                temp.add(e);
            }
        }
        return temp;
    }

    public static void removeInactiveElection(Election election) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/deleteElection/" + election.getId())
                .build().encode().toUri();
        restTemplate.delete(uri);
    }

    public static List<Constituency> getCurrentConstituency(Election election) {
        List<Election> elections = getElections();
        for (Election e : elections) {
            if (e.getId() == election.getId()) {
                return e.getConstituencies();
            }
        }
        return null;
    }

    public static void electionSetConstituency(Election election, List<Constituency> list) {
        for (Election e : getElections()) {
            if (e.getId() == election.getId()) {
                e.setConstituencies(list);
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setMessageConverters(getMessageConverters());
                URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/updateElection/" + e.getId())
                        .queryParam("id", e.getId())
                        .queryParam("startDate", e.getStartDate())
                        .queryParam("finishDate", e.getFinishDate())
                        .queryParam("electionType", e.getElectionType())
                        .queryParam("listElectionList", e.getListElectionList())
                        .queryParam("isActive", e.getActive())
                        .queryParam("electionName", e.getElectionName())
                        .queryParam("constituencies", e.getConstituencies()).build().encode().toUri();
                restTemplate.put(uri, Election.class);
            }
            }
        }


    public static void show() {
        for (Election e : getElections()) {
            System.out.println(e);
        }
    }

    public static List<Candidate> getCandidatesElection(Election election, Constituency constituency, ElectoralParty electoralParty) {
        List<Candidate> candidates = new ArrayList<>();
        List<Constituency> constituencies = ConstituencyController.getConstituencies();
        try {
            for (Constituency c : constituencies) {
                if (c.getId() == constituency.getId()) {
                    for (ElectionList cc : c.getElectionLists()) {
                        if (cc.getElectoralParty().getId() == electoralParty.getId())
                            candidates = cc.getCandidates();
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Null w getCandidatesElection");
        }
        return candidates;
    }

    public static List<ElectionList> getElectionIdByConstituencyID(Constituency constituency) {
        List<ElectionList> elist = new ArrayList<>();
        List<ElectionList> electionLists = ElectionListController.getElectionLists();
        try {
            for (ElectionList el : electionLists) {
                if (el.getConstituency().getId() == constituency.getId()) {
                        elist.add(el);
                    }
                }
            } catch (NullPointerException e) {
            System.out.println("Null w getCandidatesElection");
        }
        return elist;
    }

}

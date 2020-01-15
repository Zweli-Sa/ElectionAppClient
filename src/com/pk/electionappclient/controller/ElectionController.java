package com.pk.electionappclient.Controller;

import com.pk.electionappclient.domain.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.*;

import static com.pk.electionappclient.Controller.ClientController.*;
import static com.pk.electionappclient.Controller.ConstituencyController.constituenciesDB;
import static com.pk.electionappclient.Controller.ElectionListController.electionList;

public class ElectionController {

    private final static ElectionType presidential = new ElectionType(1, "Prezydenckie");
    private final static ElectionType parliamentary = new ElectionType(2, "Parlamentarne");

    private static List<Election> electionsDB = new ArrayList<>();
    private static List<Election> inActiveElectionsDB = new ArrayList<>();
    private static List<Election> activeElectionsDB = new ArrayList<>();


    public static List<Election> getElections() {
        RestTemplate restTemplate = new RestTemplate();
        List<Election> electionList = new ArrayList<>();
        try {
            Election[] elections = restTemplate.getForObject("http://localhost:8080/v1/election/getElectoralParties", Election[].class);
            electionList= Arrays.asList(elections);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return electionList;
    }


    public static void clearInactiveElectionList() {
        inActiveElectionsDB.clear();
    }

    public static List<Election> createElectionDay(int id, LocalDateTime startDate, LocalDateTime finishDate, ElectionType electionType, List<ElectionList> list) {
        clearCandidateTempList();
        if(!startDate.equals(null) || !finishDate.equals(null) || !electionType.equals(null) || !list.equals(null)) {
            Election election = new Election(id, startDate, finishDate, electionType, list);
            electionsDB.add(election);

            RestTemplate restTemplate = new RestTemplate();

            URI uri = null;
            try {
                uri = new URI("http://localhost:8080/v1/election/createElection");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-COM-PERSIST", "true");
            headers.set("X-COM-LOCATION", "PL");
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Election> request = new HttpEntity<>(election,headers);
            restTemplate.postForEntity(uri, request, String.class);
        }
        return electionsDB;
    }

    public static List<Election> createElectionDayTest(int id, LocalDateTime startDate, LocalDateTime finishDate, ElectionType electionType, List<ElectionList> list, Boolean isActive, String name) {
        candidateFinalList = new ArrayList<>();
        candidateTempList = new ArrayList<>();
        if(!startDate.equals(null) || !finishDate.equals(null) || !electionType.equals(null) || !list.equals(null)) {
            Election election = new Election(id, startDate, finishDate, electionType, list, isActive, name);
            electionsDB.add(election);

            RestTemplate restTemplate = new RestTemplate();

            URI uri = null;
            try {
                uri = new URI("http://localhost:8080/v1/election/createElection");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-COM-PERSIST", "true");
            headers.set("X-COM-LOCATION", "PL");
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Election> request = new HttpEntity<>(election,headers);
            restTemplate.postForEntity(uri, request, String.class);
        }
        return electionsDB;
    }

    public static List<Election> getInActiveElections() {
        inActiveElectionsDB = new ArrayList<>();
        for (Election e : electionsDB) {
            if (!e.getActive()) {
                inActiveElectionsDB.add(e);
            }
        }
        return inActiveElectionsDB;
    }


    public static List<Election> getActiveElections() {
        activeElectionsDB = new ArrayList<>();
        for (Election e : electionsDB) {
            if (e.getActive()) {
                activeElectionsDB.add(e);
            }
        }
        return activeElectionsDB;
    }

    public static void removeInactiveElection(Election election) {
        electionsDB.removeIf(e -> (e.getId() == election.getId()));
    }

    public static List<Constituency> getCurrentConstituency(Election election) {
        for (Election e : electionsDB) {
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

                Map<String, String> params = new HashMap<>();
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.put("http://localhost:8080/v1/election/updateElection/" + e.getId(), e, params); //TODO: do sprawdzenia
            }
            }
        }


    public static void show() {
        for (Election e : electionsDB) {
            System.out.println(e);
        }
    }

    public static void initElectionsDB() {
        electionsDB = new ArrayList<>();
        electionsDB.add(new Election(1l, null, null, parliamentary, null, false, "Wybory parlamentarne 2012"));
        electionsDB.add(new Election(4l, null, null, parliamentary, null, false, "Wybory parlamentarne 2012"));
        electionsDB.add(new Election(5l, null, null, parliamentary, null, false, "Wybory parlamentarne 2012"));
        electionsDB.add(new Election(2l, null, null, parliamentary, null, true, "Wybory parlamentaren 2014"));
    }

    public static List<Candidate> getCandidatesElection(Election election, Constituency constituency, ElectoralParty electoralParty) {
        List<Candidate> candidates = new ArrayList<>();
        try {
            for (Constituency c : constituenciesDB) {
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
        try {
            for (ElectionList el : electionList) {
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

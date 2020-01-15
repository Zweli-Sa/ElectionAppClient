package com.pk.electionappclient.Controller;

import com.pk.electionappclient.domain.Candidate;
import com.pk.electionappclient.domain.Constituency;
import com.pk.electionappclient.domain.ElectionList;
import com.pk.electionappclient.domain.ElectoralParty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.pk.electionappclient.Controller.AppController.popUpError;

public class ElectionListController {

    public static List<ElectionList> electionList = new ArrayList<>();

    public static List<ElectionList> newPresElectionList(int id, List<Candidate> candidates) throws NullPointerException{
        if (!candidates.isEmpty()) {
            System.out.println("newPresElectionList");
            ElectionList election = new ElectionList(id, candidates);
            electionList.add(election);

            RestTemplate restTemplate = new RestTemplate();

            URI uri = null;
            try {
                uri = new URI("http://localhost:8080/v1/election/createElectionList");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-COM-PERSIST", "true");
            headers.set("X-COM-LOCATION", "PL");
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<ElectionList> request = new HttpEntity<>(election,headers);
            restTemplate.postForEntity(uri, request, String.class);
        }
        else {
            popUpError("Dodaj kandydata do listy wyborzcej");
            throw new NullPointerException();
        }
        return electionList;
    }
    public static boolean containPartyElectionlist(Constituency constituency, ElectoralParty electoralParty) {
        if(electionList.stream().filter(o -> o.getElectoralParty().getId()==(electoralParty.getId()) && o.getConstituency().getId() == constituency.getId()).findAny().isPresent()) {
            return true;
        }
        return false;
    }

    public static boolean candidateInAnotherConstituency(Constituency constituency, List<Candidate> candidateList) {
        for (Candidate c : candidateList) {
            if(electionList.stream().filter(o -> o.getConstituency().getElection().getId() == constituency.getElection().getId() && o.getCandidates().contains(c)).findAny().isPresent()) {
                return true;
            }
        }
        return false;
    }

    public static List<ElectionList> newParlElectionList(int id, List<Candidate> candidates, ElectoralParty electoralParty, Constituency constituency) throws NullPointerException{
        if (electionList.isEmpty()) {
            ElectionList election = new ElectionList(id, candidates, electoralParty, constituency);
            electionList.add(election);
            RestTemplate restTemplate = new RestTemplate();

            URI uri = null;
            try {
                uri = new URI("http://localhost:8080/v1/election/createElectionList");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-COM-PERSIST", "true");
            headers.set("X-COM-LOCATION", "PL");
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<ElectionList> request = new HttpEntity<>(election,headers);
            restTemplate.postForEntity(uri, request, String.class);
        } else {
            if (containPartyElectionlist(constituency, electoralParty)) {
                for (ElectionList el : electionList) {
                    if (constituency.getId() == el.getConstituency().getId() && electoralParty.getId() == el.getElectoralParty().getId()) {
                        System.out.println(electionList.indexOf(el));
                        electionList.get(electionList.indexOf(el)).getCandidates().addAll(candidates);
                    }
                }
            }else {
                ElectionList election = new ElectionList(id, candidates, electoralParty, constituency);
                electionList.add(election);
                RestTemplate restTemplate = new RestTemplate();

                URI uri = null;
                try {
                    uri = new URI("http://localhost:8080/v1/election/createElectionList");
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                HttpHeaders headers = new HttpHeaders();
                headers.set("X-COM-PERSIST", "true");
                headers.set("X-COM-LOCATION", "PL");
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<ElectionList> request = new HttpEntity<>(election,headers);
                restTemplate.postForEntity(uri, request, String.class);
            }
        }
        return electionList;
    }
    public static List<ElectionList> getParlElectionListByConstituencyID(Constituency constituency) {
        List<ElectionList> temp = new ArrayList<>();
        for (ElectionList e : electionList) {
            if (e.getConstituency().getId() == constituency.getId()) {
                temp.add(e);
            }
        }
        System.out.println("temp: " + temp);
        return temp;
    }

    public static void clearElectionList() {
        electionList = new ArrayList<>();
    }

    public static void showElectionListDB() {
        System.out.println(electionList);
    }


}
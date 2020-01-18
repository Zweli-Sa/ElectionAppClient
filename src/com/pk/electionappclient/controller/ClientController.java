package com.pk.electionappclient.Controller;

import com.pk.electionappclient.domain.*;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

public class ClientController {

    private static final String URL = "http://localhost:8080/v1/election";

    public static void createCandidate(Candidate candidate) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI url = UriComponentsBuilder.fromHttpUrl(URL + "/createCandidate")
                .queryParam("id", candidate.getId())
                .queryParam("name", candidate.getName())
                .queryParam("lastname", candidate.getLastname())
                .queryParam("education", candidate.getEducation())
                .queryParam("placeOfResidence", candidate.getPlaceOfResidence())
                .queryParam("voteResults", Arrays.asList(new VoteResult()))
                .queryParam("electionList", candidate.getElectionList())
                .queryParam("electoralParty", candidate.getElectoralParty()).build().encode().toUri();
        System.out.println(url.toString());
        restTemplate.postForObject(url, null, Candidate.class);
    }

    public static List<Candidate> getCandidatesByParty(ElectoralParty party) {
        List<Candidate> candidates = getCandidates();
        List<Candidate> filteredCandidates= candidates.stream().filter(o -> o.getElectoralParty().getId().equals(party.getId()))
                .collect(Collectors.toList());
        System.out.println(filteredCandidates); //TODO: do usuniecia
        return filteredCandidates;
    }

    public static void updateCandidate(Long candidateId, Candidate candidate) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/updateCandidate/" + candidateId)
                .queryParam("id", candidate.getId())
                .queryParam("name", candidate.getName())
                .queryParam("lastname", candidate.getLastname())
                .queryParam("education", candidate.getEducation())
                .queryParam("placeOfResidence", candidate.getPlaceOfResidence())
                .queryParam("voteResults", candidate.getVoteResults())
                .queryParam("electionList", candidate.getElectionList())
                .queryParam("electoralParty", candidate.getElectoralParty()).build().encode().toUri();
        restTemplate.put(uri, Election.class);
    }


    public static List<Candidate> getCandidates() {

//        List<Candidate> candidateList = new ArrayList<>();
//        try {
//            Candidate[] candidates = restTemplate.getForObject("http://localhost:8080/v1/election/getCandidates", Candidate[].class);
//            candidateList = Arrays.asList(candidates);
//        } catch (RestClientException e) {
//            e.printStackTrace();
//        }

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getCandidates")
                .build().encode().toUri();
        Candidate[] boardResponse = restTemplate.getForObject(uri, Candidate[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new Candidate[0]));
    }

    public static List<Candidate> addCandidate(String name, String lastName, Education education, String placeOfResidence, ElectoralParty electoralParty) {
        Candidate candidate = new Candidate(0L, name, lastName, education, placeOfResidence, null, null,  electoralParty);
        createCandidate(candidate);
        List<Candidate> candidates = getCandidates();
        return candidates;
    }

    public static List<Candidate> removeCadidate(Candidate candidate) {

//        Map<String, String> params = new HashMap<String, String>();
//
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.delete ("http://localhost:8080/v1/election/deleteCandidate/" + candidate.getId(),  params );

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/deleteCandidate/" + candidate.getId())
                .build().encode().toUri();
        restTemplate.delete(uri);
        return getCandidates();
    }

//    public static List<Candidate> addCandidateToTempList(Candidate candidate) {
//        List<Candidate> candidateTempList = getCandidates();
//         if (!candidateTempList.contains(candidate)) {
//            candidateTempList.add(candidate);
//        } else{
//            popUpError("Kandydat jest już na liście");
//        }
//        return candidateTempList;
//    }

    public static Candidate checkIfCandidateExists(Candidate candidate) { //TODO: metoda do poprawy
        List<Candidate> candidates = getCandidates();

        if (candidates.contains(candidate)) {
            return candidate;
        } else {
            return null;
        }
    }

    public static List<ElectoralParty> getParties() {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
//        List<ElectoralParty> electoralPartyList = new ArrayList<>();
//        try {
//            ElectoralParty[] electoralParties = restTemplate.getForObject("http://localhost:8080/v1/election/getElectoralParties", ElectoralParty[].class);
//            electoralPartyList = Arrays.asList(electoralParties);
//        } catch (RestClientException e) {
//            e.printStackTrace();
//        }
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getElectoralParties")
                .build().encode().toUri();
        ElectoralParty[] boardResponse = restTemplate.getForObject(uri, ElectoralParty[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new ElectoralParty[0]));
    }

    public static List<HttpMessageConverter<?>>  getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        return messageConverters;
    }

    public static User checkLoginData(String login , String password) {
        return null;
    }
}

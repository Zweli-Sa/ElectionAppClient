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
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setMessageConverters(getMessageConverters());
            URI url = UriComponentsBuilder.fromHttpUrl(URL + "/createCandidate")
                    .queryParam("id", candidate.getId())
                    .queryParam("name", candidate.getName())
                    .queryParam("lastname", candidate.getLastname())
                    .queryParam("education", candidate.getEducation())
                    .queryParam("placeOfResidence", candidate.getPlaceOfResidence()).build().encode().toUri();
            restTemplate.postForObject(url, null, Candidate.class);
        } catch (Exception ex) {

        }
    }

    public static List<Candidate> getCandidatesByParty(ElectoralParty party) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getCandidatesByParty/" + party.getId())
                .build().encode().toUri();
        Candidate[] boardResponse = restTemplate.getForObject(uri, Candidate[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new Candidate[0]));
    }

    public static void updateCandidate(Long candidateId, Candidate candidate) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/updateCandidate/" + candidateId)
                .queryParam("id", candidate.getId())
                .queryParam("name", candidate.getName())
                .queryParam("lastname", candidate.getLastname())
                .queryParam("education", candidate.getEducation())
                .queryParam("placeOfResidence", candidate.getPlaceOfResidence()).build().encode().toUri();
        restTemplate.put(uri, Election.class);
    }

    public static void setCandidateElectoralParty(Long candidateId, Long electoralPartyId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/setCandidateElectoralParty/" + candidateId + "/" + electoralPartyId)
                .build().encode().toUri();
        restTemplate.getForObject(uri, Candidate.class);
    }


    public static List<Candidate> getCandidates() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getCandidates")
                .build().encode().toUri();
        Candidate[] boardResponse = restTemplate.getForObject(uri, Candidate[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new Candidate[0]));
    }

    private static Long findCandidateIdByOtherData(List<Candidate> candidates, Candidate candidate) {
        List<Candidate> foundCandidates = candidates.stream()
                .filter(o ->  o.getName().equals(candidate.getName())
                        && o.getLastname().equals(candidate.getLastname())
                )
                .collect(Collectors.toList());

        List<Candidate> filteredCandidates = foundCandidates.stream()
                .filter(o ->  o.getEducation().equals(candidate.getEducation())
                        && o.getPlaceOfResidence().equals(candidate.getPlaceOfResidence())
                )
                .collect(Collectors.toList());
        return filteredCandidates.get(0).getId();
    }

    public static List<Candidate> addCandidate(String name, String lastName, Education education, String placeOfResidence, ElectoralParty electoralParty) {
        Candidate candidate = new Candidate(0L, name, lastName, education.toString(), placeOfResidence);
        createCandidate(candidate);
        List<Candidate> candidates = getCandidates();
        setCandidateElectoralParty(findCandidateIdByOtherData(candidates, candidate), electoralParty.getId());
        return candidates;
    }

    public static List<Candidate> removeCandidate(Candidate candidate) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/deleteCandidate/" + candidate.getId())
                .build().encode().toUri();
        restTemplate.delete(uri);
        return getCandidates();
    }

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

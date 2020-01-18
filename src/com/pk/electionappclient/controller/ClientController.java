package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;
import com.pk.electionappclient.httpresponser.HttpResponser;
import com.pk.electionappclient.mapper.JsonMapper;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static com.pk.electionappclient.controller.AppController.popUpError;
import static com.pk.electionappclient.controller.ElectionController.*;
import static java.util.Optional.ofNullable;

public class ClientController {

    public static int id = 0;

    private static final String URL = "http://localhost:8080/v1/election";

    public static List<Candidate> candidateTempList = new ArrayList<>();
    public static List<Candidate> candidateFinalList = new ArrayList<>();


    public static Long createCandidate(Candidate candidate) {
        Long candidateId = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setMessageConverters(getMessageConverters());
            URI url = UriComponentsBuilder.fromHttpUrl(URL + "/createCandidate")
                    .queryParam("id", candidate.getId())
                    .queryParam("name", candidate.getName())
                    .queryParam("lastname", candidate.getLastname())
                    .queryParam("education", candidate.getEducation())
                    .queryParam("placeOfResidence", candidate.getPlaceOfResidence()).build().encode().toUri();
            Candidate boardResponse = restTemplate.postForObject(url, null, Candidate.class);
            candidateId = boardResponse.getId();
        } catch (Exception ex) {
        }
        return candidateId;
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

    public static ElectoralParty getElectoralPartyByCandidateId(Long candidateId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getElectoralPartyByCandidateId/" + candidateId)
                .build().encode().toUri();
        ElectoralParty boardResponse = restTemplate.getForObject(uri, ElectoralParty.class);
        return ofNullable(boardResponse).orElse(new ElectoralParty());
    }

//--------------------votepanel-----------------------------------------------------------------------------------------

    public static Constituency getConstituencyListByUserCityId(Election election, int userId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getConstituency/" + election.getId() + "/" + userId)
                .build().encode().toUri();
        Constituency boardResponse = restTemplate.getForObject(uri, Constituency.class);
        return ofNullable(boardResponse).orElse(new Constituency());
    }

    public static List<ElectionList> getElectionListByConstituency(Constituency constituency) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getElectionListByConstituency/" + constituency.getId())
                .build().encode().toUri();
        ElectionList[] boardResponse = restTemplate.getForObject(uri, ElectionList[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new ElectionList[0]));
    }

    public static List<ElectoralParty> getElectoralPartiesByElectionList(List<ElectionList> electionListList) { //TODO: check it
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getElectoralPartiesByElectionList")
                .build().encode().toUri();
        ElectoralParty[] boardResponse = restTemplate.getForObject(uri, ElectoralParty[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new ElectoralParty[0]));
    }

    public static List<Candidate> getCandidateByElectionListElectoralParty(List<ElectionList> electionList, ElectoralParty electoralParty) { //TODO: check it
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getCandidateByElectionListElectoralParty/" + electoralParty.getId())
                .build().encode().toUri();
        Candidate[] boardResponse = restTemplate.getForObject(uri, Candidate[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new Candidate[0]));
    }

// ---------------------------------------------------------------------------------------------------------------------

//-----VOTE-Results-----------------------------------------------------------------------------------------------------





// ---------------------------------------------------------------------------------------------------------------------
    public static List<Candidate> getCandidates() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getCandidates")
                .build().encode().toUri();
        Candidate[] boardResponse = restTemplate.getForObject(uri, Candidate[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new Candidate[0]));
    }

    public static void setCandidateElectoralParty(Long candidateId, Long electoralPartyId) {
        if (candidateId != null) {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setMessageConverters(getMessageConverters());
            URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/setCandidateElectoralParty/" + candidateId + "/" + electoralPartyId)
                    .build().encode().toUri();
            restTemplate.getForObject(uri, Candidate.class);
        }
    }

//    private static Long findCandidateIdByOtherData(List<Candidate> candidates, Candidate candidate) {
//        List<Candidate> foundCandidates = candidates.stream()
//                .filter(o ->  o.getName().equals(candidate.getName())
//                        && o.getLastname().equals(candidate.getLastname())
//                )
//                .collect(Collectors.toList());
//
//        List<Candidate> filteredCandidates = foundCandidates.stream()
//                .filter(o ->  o.getEducation().equals(candidate.getEducation())
//                        && o.getPlaceOfResidence().equals(candidate.getPlaceOfResidence())
//                )
//                .collect(Collectors.toList());
//        return filteredCandidates.get(0).getId();
//    }

    public static List<Candidate> addCandidate(String name, String lastName, Education education, String placeOfResidence, ElectoralParty electoralParty) throws IOException {
        //list.add(new Candidate(1l, name, lastName, education, placeOfResidence));
        Candidate candidate = new Candidate(0L, name, lastName, education.toString(), placeOfResidence);
        Long candidateId = createCandidate(candidate);
        List<Candidate> candidates = getCandidates();
        setCandidateElectoralParty(candidateId, electoralParty.getId());
        return candidates;
    }

    public static List<Candidate> removeCadidate(Candidate candidate) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/deleteCandidate/" + candidate.getId())
                .build().encode().toUri();
        restTemplate.delete(uri);
        return getCandidates();
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

    public static List<ElectoralParty> getParties() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getElectoralParties")
                .build().encode().toUri();
        ElectoralParty[] boardResponse = restTemplate.getForObject(uri, ElectoralParty[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new ElectoralParty[0]));
    }

    public static List<ElectoralParty> getPartyByConstituency(Constituency constituency) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getPartyByConstituency/" + constituency.getId())
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
}

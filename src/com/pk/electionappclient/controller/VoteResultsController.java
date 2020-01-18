package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.pk.electionappclient.Main.globalID;
import static com.pk.electionappclient.controller.ClientController.getMessageConverters;
import static java.util.Optional.ofNullable;

public class VoteResultsController {
    public static List<VoteResult> voteResultsDB = new ArrayList<>();

    private static final String URL = "http://localhost:8080/v1/election";

    public static Long createVoteResult() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI url = UriComponentsBuilder.fromHttpUrl(URL + "/createVoteResult")
                .queryParam("id", 0L).build().encode().toUri();
        Candidate boardResponse = restTemplate.postForObject(url, null, Candidate.class);
        return boardResponse.getId();
    }

    public static void voteResultUpdateParametr(Long voteResultId, String parametr, String value) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/updateVoteResult/" + voteResultId + "/" + parametr + "/" + value)
                .build().encode().toUri();
        restTemplate.put(uri, Election.class);
    }

    public static void voteForCandidate(Election election, Candidate candidate, Constituency constituency) {
        //VoteResult voteResult = new VoteResult(globalID++, election, candidate, constituency, LocalDateTime.now());
        Long voteResultId = createVoteResult();
        Long value = (Long)election.getId();
        voteResultUpdateParametr(voteResultId, "election_id", value.toString());
        String value2 = candidate.getId().toString();
        voteResultUpdateParametr(voteResultId, "candidate_id", value2);
        String value3 = constituency.getId().toString();
        voteResultUpdateParametr(voteResultId, "constituency_id", value3);
    }

    public static int getResults(Election election, Candidate candidate, Constituency constituency) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getVotesForCandidateInConstituency/" + candidate.getId() + "/" + constituency.getId())
                .build().encode().toUri();
        VoteResult[] boardResponse = restTemplate.getForObject(uri, VoteResult[].class);
        List<VoteResult> voteResultsForCandidate = Arrays.asList(ofNullable(boardResponse).orElse(new VoteResult[0]));

        System.out.println("Liczba oddanych glosow na :" + candidate.getId() +" " + voteResultsForCandidate.size());
        return voteResultsForCandidate.size();
    }


}

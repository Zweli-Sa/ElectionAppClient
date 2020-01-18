package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.Candidate;
import com.pk.electionappclient.domain.Constituency;
import com.pk.electionappclient.domain.Election;
import com.pk.electionappclient.domain.VoteResult;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.pk.electionappclient.Main.globalID;

public class VoteResultsController {
    public static List<VoteResult> voteResultsDB = new ArrayList<>();

    public static void voteForCandidate(Election election, Candidate candidate, Constituency constituency) {
        VoteResult voteResult = new VoteResult(globalID++, election, candidate, constituency, LocalDateTime.now());
        voteResultsDB.add(voteResult);
    }

    public static int getResults(Election election, Candidate candidate, Constituency constituency) {
        List<VoteResult> temp = new ArrayList<>();
        temp = voteResultsDB.stream().filter(o -> o.getConstituency().getId()==(constituency.getId()) && o.getCandidate().getId()==candidate.getId()).collect(Collectors.toList());
        System.out.println("Liczba oddanych glosow na :" + candidate.getId() +" " + temp.size());
        return temp.size();
    }


}

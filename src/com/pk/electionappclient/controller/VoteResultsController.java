package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.pk.electionappclient.Main.globalID;
import static com.pk.electionappclient.controller.AppController.popUpError;

public class VoteResultsController {
    public static List<VoteResult> voteResultsDB = new ArrayList<>();

    public static void voteForCandidate(User user, Election election, Candidate candidate, Constituency constituency) {
        List<VoteResult> temp = new ArrayList<>();
        if (!voteResultsDB.stream().filter(o -> o.getElection().getId() == (election.getId()) && o.getUser().getId() == user.getId()).findFirst().isPresent()) {
            VoteResult voteResult = new VoteResult(user, globalID++, election, candidate, constituency, LocalDateTime.now());
            voteResultsDB.add(voteResult);
        } else {
            popUpError("Glos zostal juz oddany");
        }

    }

    public static int getResults(Election election, Candidate candidate, Constituency constituency) {
        List<VoteResult> temp = new ArrayList<>();
        temp = voteResultsDB.stream().filter(o -> o.getConstituency().getId()==(constituency.getId()) && o.getCandidate().getId()==candidate.getId()).collect(Collectors.toList());
        System.out.println("Liczba oddanych glosow na :" + candidate.getId() +" " + temp.size());
        return temp.size();
    }


}

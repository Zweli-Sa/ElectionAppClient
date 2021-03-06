package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.pk.electionappclient.Main.globalID;
import static com.pk.electionappclient.controller.AppController.popUpError;
import static com.pk.electionappclient.controller.ElectionController.getCandidatesByElection;

public class VoteResultsController {
    public static List<VoteResult> voteResultsDB = new ArrayList<>();

    public static void voteForParlCandidate(User user, Election election, Candidate candidate, Constituency constituency) {
        List<VoteResult> temp = new ArrayList<>();
        if (!voteResultsDB.stream().filter(o -> o.getElection().getId() == (election.getId()) && o.getUser().getId() == user.getId()).findFirst().isPresent()) {
            VoteResult voteResult = new VoteResult(user, globalID++, election, candidate, constituency, LocalDateTime.now());
            voteResultsDB.add(voteResult);
        } else {
            popUpError("Glos zostal juz oddany");
        }

    }
    public static void voteForPresCandidate(User user, Election election, Candidate candidate) {
        List<VoteResult> temp = new ArrayList<>();
        if (!voteResultsDB.stream().filter(o -> o.getElection().getId() == (election.getId()) && o.getUser().getId() == user.getId()).findFirst().isPresent()) {
            VoteResult voteResult = new VoteResult(user, globalID++, election, candidate, null, LocalDateTime.now());
            voteResultsDB.add(voteResult);
        } else {
            popUpError("Glos zostal juz oddany");
        }

    }

    public static int getParlResults(Election election, Candidate candidate, Constituency constituency) {
        List<VoteResult> temp = new ArrayList<>();
        temp = voteResultsDB.stream().filter(o -> o.getConstituency().getId()==(constituency.getId()) && o.getCandidate().getId()==candidate.getId()).collect(Collectors.toList());
        System.out.println("Liczba oddanych glosow na :" + candidate.getId() +" " + temp.size());
        return temp.size();
    }



    public static int getPresResults(Election election, Candidate candidate) {
        List<VoteResult> temp = new ArrayList<>();
        temp = voteResultsDB.stream().filter(o -> o.getElection().getId()==(election.getId()) && o.getCandidate().getId()==candidate.getId()).collect(Collectors.toList());
        System.out.println("Liczba oddanych glosow na :" + candidate.getId() +" " + temp.size());
        return temp.size();
    }



}

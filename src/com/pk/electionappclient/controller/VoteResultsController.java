package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.pk.electionappclient.Main.globalID;
import static com.pk.electionappclient.controller.ConstituencyController.warszawa;

public class VoteResultsController {
    public static List<VoteResult> voteResultsDB = new ArrayList<>();  // baza VoteResults


    public static void voteForCandidate(User user, Election election, Candidate candidate, Constituency constituency) {
        VoteResult voteResult = new VoteResult(user, globalID++, election, candidate, constituency, LocalDateTime.now());
        voteResultsDB.add(voteResult); // Dodanie do bazy VoteResult
    }



}

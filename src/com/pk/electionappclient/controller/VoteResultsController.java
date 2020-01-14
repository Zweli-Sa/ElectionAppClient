package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.Candidate;
import com.pk.electionappclient.domain.Constituency;
import com.pk.electionappclient.domain.Election;
import com.pk.electionappclient.domain.VoteResult;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.pk.electionappclient.Main.globalID;

public class VoteResultsController {
    public static List<VoteResult> voteResultsDB = new ArrayList<>();

    public static void voteForCandidate(Election election, Candidate candidate, Constituency constituency) {
        VoteResult voteResult = new VoteResult(globalID, election, candidate, constituency, LocalDateTime.now());
        voteResultsDB.add(voteResult);
    }



}

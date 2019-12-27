package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.Candidate;
import com.pk.electionappclient.domain.Election;
import com.pk.electionappclient.domain.Report;
import com.pk.electionappclient.domain.User;
import com.pk.electionappclient.domain.VoteResult;
import com.pk.electionappclient.httpresponser.HttpResponser;
import com.pk.electionappclient.mapper.JsonMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientController {

    public static List<Candidate> getCandidates() {
        return new ArrayList<>();
    }

    public static User getUser(Long id) throws IOException {
        return JsonMapper.mapToObject(HttpResponser.get("/v1/user/getUser/" + id));
    }

    public static boolean checkLoginData() {
        return true;
    }

    public static List<Report> getUserReports(Long id) {
        return new ArrayList<>();
    }

    public static List<VoteResult> getVoteResults() {
        return new ArrayList<>();
    }

    public static List<VoteResult> getVoteResultByElection(Election election) {
        return new ArrayList<>();
    }
}

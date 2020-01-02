package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.Candidate;
import com.pk.electionappclient.domain.City;
import com.pk.electionappclient.domain.Constituency;
import com.pk.electionappclient.domain.ElectionList;

import java.util.ArrayList;
import java.util.List;

public class ConstituencyController {
    private static List<Constituency> constituenciesDB = new ArrayList<>();


    public static List<Constituency> createConstituency(Long id, String name, List<City> cityList) {
        constituenciesDB.add(new Constituency(id, name, cityList));
        return constituenciesDB;
    }

    public static void showConstituencies() {
        for (Constituency c : constituenciesDB) {
            System.out.println(c);
        }
    }
}

package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;

import java.util.ArrayList;
import java.util.List;

public class ConstituencyController {
    public static List<Constituency> constituenciesDB = new ArrayList<>();


//    public static List<Constituency> createConstituency(Long id, String name, List<City> cityList) {
//        constituenciesDB.add(new Constituency(id, name, cityList));
//        return constituenciesDB;
//    }
    public static List<Constituency> createConstituency(Long id, String name, List<City> cityList, Election election) {
        constituenciesDB.add(new Constituency(id, name, cityList, election));
        return constituenciesDB;
    }

    public static List<Constituency> getConstituenciesDB() {
        return constituenciesDB;
    }

    public static void addConstituencyElection(Election election, List<Constituency> list, Long constituencyId) {
        List<Constituency> temp = new ArrayList<>();
        for (Constituency c : constituenciesDB) {
            if (c.getId() == constituencyId) {
                temp.add(c);
            }
        }
        election.setConstituencies(temp);
//        List<Constituency> temp = new ArrayList<>();
//        for (Constituency c : constituenciesDB) {
//            if (c.getElection().getId() == election.getId()) {
//                temp.add(c);
//            }
//        }
//        System.out.println(temp);
//        return temp;
    }
    public static void showConstituencies() {
        for (Constituency c : constituenciesDB) {
            System.out.println(c);
        }
    }


}

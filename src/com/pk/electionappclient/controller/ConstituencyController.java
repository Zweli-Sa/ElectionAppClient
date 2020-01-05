package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static com.pk.electionappclient.controller.AppController.popUpError;

public class ConstituencyController {
    public static List<Constituency> constituenciesDB = new ArrayList<>();

    public static List<Constituency> createConstituency(Long id, String name, List<City> cityList, Election election) {
        if (containsCity(cityList, election)) {
            popUpError("Blad, miasto juz jest dodane do okregu wyborczego");
        } else {
            constituenciesDB.add(new Constituency(id, name, cityList, election));
        }
        return constituenciesDB;
    }

    public static boolean containsCity(List<City> cityList, Election election) {
        for (City city : cityList){
            if(constituenciesDB.stream().filter(o -> o.getElection().getId()==(election.getId()) && o.getCityList().contains(city)).findAny().isPresent()) {
                //o.getElection().getId()==(election.getId()) &&
                return true;
            }
        }
        return false;
    }


    public static List<Constituency> getConstituenciesDB() {
        return constituenciesDB;
    }

    public static List<Constituency> getConstituencyByElectionID(Election election) {
        List<Constituency> temp = new ArrayList<>();
        for (Constituency c : constituenciesDB) {
            if (c.getElection().getId() == election.getId()) {
                temp.add(c);
            }
        }
        System.out.println("temp: " + temp);
        return temp;
    }
    public static void showConstituencies() {
        for (Constituency c : constituenciesDB) {
            System.out.println(c);
        }
    }
}




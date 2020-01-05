package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static com.pk.electionappclient.controller.AppController.popUpError;

public class ConstituencyController {
    public static List<City> citiesDB = new ArrayList<>();
    public static List<City> citiesTempList = new ArrayList<>();
    public static List<Constituency> constituenciesDB = new ArrayList<>();

    private static City krakow = new City(10l, "Kraków");
    private static City warszawa = new City(20l, "Warszawa");
    private static City wroclaw = new City(30l, "Wroclaw");


    public static List<City> getCitiesDB() {
        return citiesDB;
    }

    public static List<City> getCitiesTempList() {
        return citiesTempList;
    }
    public static List<City> addCityToTempList(City city) {
        //citiesTempList = new ArrayList<>();
        if (!citiesTempList.contains(city)) {
            citiesTempList.add(city);
        } else{
            popUpError("Miasto jest już na liście");
        }
        return citiesTempList;
    }

    public static List<City> clearCityTempList() {
        citiesTempList = new ArrayList<>();
        return citiesTempList;
    }

    public static List<City> removeCityTempList(City city) {
        citiesTempList.remove(city);
        return citiesTempList;
    }



    public static List<Constituency> createConstituency(Long id, String name, List<City> cityList, Election election) {
        if (containsCity(cityList, election)) {
            popUpError("Błąd! Miasto zostało dodane do innego okręgu wyborczego");
        } else {
            constituenciesDB.add(new Constituency(id, name, cityList, election));
        }
        return constituenciesDB;
    }

    public static boolean containsCity(List<City> cityList, Election election) {
        for (City city : cityList){
            if(constituenciesDB.stream().filter(o -> o.getElection().getId()==(election.getId()) && o.getCityList().contains(city)).findAny().isPresent()) {
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
    public static void initCityDB() {
        citiesDB.add(krakow);
        citiesDB.add(warszawa);
        citiesDB.add(wroclaw);
    }
}




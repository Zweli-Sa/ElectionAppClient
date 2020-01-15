package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static com.pk.electionappclient.controller.AppController.popUpError;

public class ConstituencyController {
    public static List<City> citiesDB = new ArrayList<>();// Baza miast
    public static List<Constituency> constituenciesDB = new ArrayList<>(); //Baza okregow



    public static List<City> getCitiesDB() {
        return citiesDB;
    } // zwraca baze Miast




    public static List<Constituency> createConstituency(Long id, String name, List<City> cityList, Election election) {
        if (containsCity(cityList, election)) {
            popUpError("Błąd! Miasto zostało dodane do innego okręgu wyborczego");
        } else {
            constituenciesDB.add(new Constituency(id, name, cityList, election)); //dodanie do bazy Constituency obiektu Constituency
        }
        return constituenciesDB;
    }



    public static List<Constituency> getConstituenciesDB() {
        return constituenciesDB;
    } //pobranie bazy






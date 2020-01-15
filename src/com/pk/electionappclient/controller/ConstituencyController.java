package com.pk.electionappclient.Controller;

import com.pk.electionappclient.domain.Candidate;
import com.pk.electionappclient.domain.City;
import com.pk.electionappclient.domain.Constituency;
import com.pk.electionappclient.domain.Election;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.pk.electionappclient.Controller.AppController.popUpError;

public class ConstituencyController {
    public static List<City> citiesDB = new ArrayList<>();
    public static List<City> citiesTempList = new ArrayList<>();
    public static List<Constituency> constituenciesDB = new ArrayList<>();

    private static City krakow = new City(10l, "Kraków");
    private static City warszawa = new City(20l, "Warszawa");
    private static City wroclaw = new City(30l, "Wroclaw");


    public static List<City> getCitiesDB() {
        RestTemplate restTemplate = new RestTemplate();
        List<City> cityList = new ArrayList<>();
        try {
            City[] cities = restTemplate.getForObject("http://localhost:8080/v1/election/getCities", City[].class);
            cityList = Arrays.asList(cities);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return cityList;
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
            Constituency constituency = new Constituency(id, name, cityList, election);
            constituenciesDB.add(constituency);

            RestTemplate restTemplate = new RestTemplate();

            URI uri = null;
            try {
                uri = new URI("http://localhost:8080/v1/election/createConstituency");
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-COM-PERSIST", "true");
            headers.set("X-COM-LOCATION", "PL");
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Constituency> request = new HttpEntity<>(constituency,headers);
            restTemplate.postForEntity(uri, request, String.class);
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

    public static void initCityDB() {
        citiesDB.add(krakow);
        citiesDB.add(warszawa);
        citiesDB.add(wroclaw);
    }
}




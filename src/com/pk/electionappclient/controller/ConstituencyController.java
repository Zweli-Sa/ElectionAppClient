package com.pk.electionappclient.controller;

import com.pk.electionappclient.domain.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import static com.pk.electionappclient.controller.AppController.popUpError;
import static com.pk.electionappclient.controller.ClientController.getMessageConverters;
import static java.util.Optional.ofNullable;

public class ConstituencyController {

    public static List<City> citiesTempList = new ArrayList<>();
    public static List<Constituency> constituenciesDB = new ArrayList<>();

    private static final String URL = "http://localhost:8080/v1/election";

    public static List<City> getCitiesDB() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getCities")
                .build().encode().toUri();
        City[] boardResponse = restTemplate.getForObject(uri, City[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new City[0]));
    }

    public static void removeCity(City city) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/deleteCity/" + city.getId())
                .build().encode().toUri();
        restTemplate.delete(uri);
    }

    public static void createCity(City city) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI url = UriComponentsBuilder.fromHttpUrl(URL + "/createCity")
                .queryParam("id", city.getId())
                .queryParam("name", city.getName()).build().encode().toUri();
        restTemplate.postForObject(url, null, City.class);
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

    public static List<Constituency> getConstituencies() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getConstituencies")
                .build().encode().toUri();
        Constituency[] boardResponse = restTemplate.getForObject(uri, Constituency[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new Constituency[0]));
    }

    public static List<Constituency> createConstituency(Long id, String name, List<City> cityList, Election election) {
        if (containsCity(cityList, election)) {
            popUpError("Błąd! Miasto zostało dodane do innego okręgu wyborczego");
        } else {
            Constituency constituency = new Constituency(0L, name);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setMessageConverters(getMessageConverters());
            URI url = UriComponentsBuilder.fromHttpUrl(URL + "/createConstituency")
                    .queryParam("id", constituency.getId())
                    .queryParam("name", constituency.getName())
                    .queryParam("description", constituency.getDescription()).build().encode().toUri();
            restTemplate.postForObject(url, null, Candidate.class);

            //Change election in constituency by electionId
            setElectionInConstituencyByElectionId(findConstituencyByName(name), election.getId());

            //Change cityList in constituency by cityId //TODO: check it
        }
        return getConstituencies();
    }

    public static Long findConstituencyByName(String name) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getConstituencies")
                .build().encode().toUri();
        Constituency[] boardResponse = restTemplate.getForObject(uri, Constituency[].class);
        List<Constituency> constituencies = Arrays.asList(ofNullable(boardResponse).orElse(new Constituency[0]));
        return constituencies.get(0).getId();
    }

    public static void setElectionInConstituencyByElectionId(Long constituencyId, Long electionId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/setElectionInConstituencyByElectionId/" + constituencyId + "/" + electionId)
                .build().encode().toUri();
        restTemplate.getForObject(uri, Constituency.class);
    }

    private static Election getElectionByConstituencyId(Long consitutencyId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getElectionByConstituencyId/" + consitutencyId)
                .build().encode().toUri();
        Election boardResponse = restTemplate.getForObject(uri, Election.class);
        return ofNullable(boardResponse).orElse(new Election());
    }

    private static List<City> getCityListByConstituencyId(Long consitutencyId) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getCityListByConstituencyId/" + consitutencyId)
                .build().encode().toUri();
        City[] boardResponse = restTemplate.getForObject(uri, City[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new City[0]));
    }

    public static boolean containsCity(List<City> cityList, Election election) {
        List<Constituency> constituencies = getConstituencies();
        for (City city : cityList){
            for (Constituency constituency : constituencies) {
                if (getElectionByConstituencyId(constituency.getId()).equals(election.getId()) && getCityListByConstituencyId(constituency.getId()).contains(city)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<Constituency> getConstituencyByElectionID(Election election) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
        URI uri = UriComponentsBuilder.fromHttpUrl(URL + "/getConstituencyByElectionID/" + election.getId())
                .build().encode().toUri();
        Constituency[] boardResponse = restTemplate.getForObject(uri, Constituency[].class);
        return Arrays.asList(ofNullable(boardResponse).orElse(new Constituency[0]));
    }
}




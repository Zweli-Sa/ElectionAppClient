package com.pk.electionappclient.Controller;

import com.pk.electionappclient.domain.Candidate;
import com.pk.electionappclient.domain.City;
import com.pk.electionappclient.domain.Constituency;
import com.pk.electionappclient.domain.Election;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.pk.electionappclient.Controller.AppController.popUpError;
import static com.pk.electionappclient.Controller.ClientController.getMessageConverters;
import static java.util.Optional.ofNullable;

public class ConstituencyController {

    private static final String URL = "http://localhost:8080/v1/election";

    public static List<City> getCities() {
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
                .queryParam("name", city.getName())
                .queryParam("users", city.getUsers()).build().encode().toUri();
        restTemplate.postForObject(url, null, City.class);
    }

    public static City checkIfCityExists(City city) { //TODO: metoda do poprawy
        List<City> cities = getCities();

        if (cities.contains(city)) {
            return city;
        } else {
            return null;
        }
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
            Constituency constituency = new Constituency(id, name, cityList, election);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setMessageConverters(getMessageConverters());
            URI url = UriComponentsBuilder.fromHttpUrl(URL + "/createConstituency")
                    .queryParam("id", constituency.getId())
                    .queryParam("name", constituency.getName())
                    .queryParam("description", constituency.getDescription())
                    .queryParam("election", constituency.getElection())
                    .queryParam("electionLists", constituency.getElectionLists())
                    .queryParam("cityList", constituency.getCityList()).build().encode().toUri();
            restTemplate.postForObject(url, null, Candidate.class);
        }
        return getConstituencies();
    }

    public static boolean containsCity(List<City> cityList, Election election) {
        List<Constituency> constituencies = getConstituencies();
        for (City city : cityList){
            if(constituencies.stream().filter(o -> o.getElection().getId()==(election.getId()) && o.getCityList().contains(city)).findAny().isPresent()) {
                return true;
            }
        }
        return false;
    }

    public static List<Constituency> getConstituencyByElectionID(Election election) {
        List<Constituency> temp = new ArrayList<>();
        List<Constituency> constituencies = getConstituencies();
        for (Constituency c : constituencies) {
            if (c.getElection().getId() == election.getId()) {
                temp.add(c);
            }
        }
        System.out.println("temp: " + temp);
        return temp;


    }
}




package com.pk.electionappclient.httpresponser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpResponser {

    private static final String URL = "http://localhost:8080";

    public static String get(String endpoint) throws IOException {
        String url = URL + endpoint;
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

        // optional default is GET
        httpClient.setRequestMethod("GET");

        //get response
        int responseCode = httpClient.getResponseCode();

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            return response.toString();
        }
    }

//    public static HttpResponse put(String body) {
//        try () {
//
//        } catch () {
//
//        }
//    }
//
//    public static HttpResponse post(String body) {
//        try () {
//
//        } catch () {
//
//        }
//    }
//
//    public static HttpResponse delete(String body) {
//        try () {
//
//        } catch () {
//
//        }
//    }
}

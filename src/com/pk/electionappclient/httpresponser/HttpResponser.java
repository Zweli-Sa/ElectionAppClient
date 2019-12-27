package com.pk.electionappclient.httpresponser;

import com.sun.deploy.net.HttpResponse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
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
    public static String post(String endpoint, String jsonBody) throws IOException {
        String url = URL + endpoint;
        HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("Content-Type", "application/json; charset=utf8");
        httpClient.setDoOutput(true);

        try (OutputStream os = httpClient.getOutputStream()) {
            byte[] input = jsonBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream()))) {

            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        return response.toString();
    }
//
//    public static HttpResponse delete(String body) {
//        try () {
//
//        } catch () {
//
//        }
//    }
}

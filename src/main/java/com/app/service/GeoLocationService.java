package com.app.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class GeoLocationService {

    private static final String API_KEY = "d758bdd6a6704da48d5d0731974642d6";
    private static final String URL = "http://api.ipstack.com/";

    public String  getGeolocation(String ipAddress) {
        RestTemplate restTemplate = new RestTemplate();
        String url = URL + ipAddress + "?access_key=" + API_KEY;  // ✅ Proper concatenation

        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        return response.getBody();

    }

}
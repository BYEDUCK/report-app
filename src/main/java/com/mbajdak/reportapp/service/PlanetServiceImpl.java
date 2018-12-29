package com.mbajdak.reportapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mbajdak.reportapp.domain.Planet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Service
public class PlanetServiceImpl extends ExternalApiCallServiceBase implements PlanetService {

    private final static String EXTERNAL_SERVICE_URL = "https://swapi.co/api/planets/";

    @Autowired
    protected PlanetServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public Planet getPlanetForName(String planetName) throws IOException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(EXTERNAL_SERVICE_URL)
                .queryParam("search", planetName);
        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        String url = getUrlFromJsonResultsContainsOne(root);
        return new Planet(planetName, url);
    }
}

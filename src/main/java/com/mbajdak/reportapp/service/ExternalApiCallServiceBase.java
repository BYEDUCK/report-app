package com.mbajdak.reportapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class ExternalApiCallServiceBase {

    RestTemplate restTemplate;
    HttpEntity<String> entity;

    protected ExternalApiCallServiceBase(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        entity = new HttpEntity<>("parameters", httpHeaders);
    }

    @SuppressWarnings("Duplicates")
    <T> List<T> getObjectsFromJsonResponse(Class<T> clazz, ResponseEntity<String> response) throws IOException {
        List<T> result = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode results = root.path("results");
        Iterator<JsonNode> iterator = results.elements();
        while (iterator.hasNext())
            result.add(mapper.readValue(iterator.next().toString(), clazz));
        return result;
    }

    <T> T getObjectFromJsonResponse(Class<T> clazz, ResponseEntity<String> response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        if (root.path("count").asText().equals("1")) {
            JsonNode results = root.path("results");
            Iterator<JsonNode> iterator = results.elements();
            if (iterator.hasNext())
                return mapper.readValue(iterator.next().toString(), clazz);
            else
                return null;
        } else
            return null;
    }

    <T> T getObjectFromJsonResponsePlain(Class<T> clazz, ResponseEntity<String> response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        return mapper.readValue(root.toString(), clazz);
    }
}

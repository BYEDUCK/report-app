package com.mbajdak.reportapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

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

    private String getFieldFromJsonResultsContainsOne(String fieldName, JsonNode root) {
        JsonNode count = root.path("count");
        if (count.toString().equals("1")) {
            JsonNode results = root.path("results");
            Iterator<JsonNode> iterator = results.elements();
            if (iterator.hasNext()) {
                JsonNode result = iterator.next();
                return result.path(fieldName).asText();
            } else
                return null;
        } else
            return null;
    }

    String getUrlFromJsonResultsContainsOne(JsonNode root) {
        return getFieldFromJsonResultsContainsOne("url", root);
    }
}

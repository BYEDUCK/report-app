package com.mbajdak.reportapp.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface PlanetService {
    String getPlanetUrlForName(String planetName) throws IOException;
}

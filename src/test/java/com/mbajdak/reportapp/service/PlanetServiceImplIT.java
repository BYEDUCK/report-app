package com.mbajdak.reportapp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PlanetServiceImplIT {

    @Autowired
    private PlanetService planetService;

    @Test
    void getPlanetUrlForName() throws IOException {
        String planetName = "Alderaan";
        String planetUrl = planetService.getPlanetUrlForName(planetName);
        assertEquals("https://swapi.co/api/planets/2/", planetUrl);
    }
}
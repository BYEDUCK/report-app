package com.mbajdak.reportapp.service;

import com.mbajdak.reportapp.domain.PlanetDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PlanetServiceImplIT {

    @Autowired
    private PlanetService planetService;

    @Test
    void getPlanetUrlForName() throws IOException {
        String planetName = "Alderaan";
        PlanetDTO planet = planetService.getPlanetForName(planetName);
        assertNotNull(planet);
        assertEquals(2, planet.getId().intValue());
        assertEquals(planetName, planet.getName());
    }
}
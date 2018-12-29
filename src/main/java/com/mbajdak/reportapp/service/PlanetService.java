package com.mbajdak.reportapp.service;

import com.mbajdak.reportapp.domain.Planet;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface PlanetService {
    Planet getPlanetForName(String planetName) throws IOException;
}

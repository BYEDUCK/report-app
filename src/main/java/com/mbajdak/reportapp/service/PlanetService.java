package com.mbajdak.reportapp.service;

import com.mbajdak.reportapp.domain.PlanetDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface PlanetService {
    PlanetDTO getPlanetForName(String planetName) throws IOException;
}

package com.mbajdak.reportapp.domain;

public class PlanetDTO extends BaseDTO {
    public PlanetDTO(String name, String url) {
        super(name, url);
    }

    public static PlanetDTO fromPlanet(Planet planet) {
        return new PlanetDTO(planet.getName(), planet.getUrl());
    }
}

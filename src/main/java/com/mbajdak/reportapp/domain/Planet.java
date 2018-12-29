package com.mbajdak.reportapp.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Planet {
    private String climate;
    private String created;
    private String diameter;
    private String edited;
    private List<String> films;
    private String gravity;
    private String name;
    @JsonProperty("orbital_period")
    private String orbitalPeriod;
    private String population;
    private List<String> residents;
    @JsonProperty("rotation_period")
    private String rotationPeriod;
    @JsonProperty("surface_water")
    private String surfaceWater;
    private String terrain;
    private String url;
}

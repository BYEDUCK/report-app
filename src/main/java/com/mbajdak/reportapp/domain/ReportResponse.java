package com.mbajdak.reportapp.domain;

import lombok.Data;

import java.util.List;

@Data
public class ReportResponse {

    private List<Report> reports;
    private Criteria criteria;

    public ReportResponse(List<Report> reports, String characterPhrase, String planetName) {
        this.reports = reports;
        criteria = new Criteria();
        criteria.setCharacterPhrase(characterPhrase);
        criteria.setPlanetName(planetName);
    }
}

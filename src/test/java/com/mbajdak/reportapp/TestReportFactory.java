package com.mbajdak.reportapp;

import com.mbajdak.reportapp.domain.Report;

public class TestReportFactory {
    public static Report getTestReport(String characterName, String planetName,
                                       Integer filmId, String filmName,
                                       Integer characterId, Integer planetId, Integer reportId) {
        Report testReport = new Report();
        testReport.setCharacterName(characterName);
        testReport.setPlanetName(planetName);
        testReport.setFilmId(filmId);
        testReport.setFilmName(filmName);
        testReport.setCharacterId(characterId);
        testReport.setPlanetId(planetId);
        testReport.setId(reportId);
        return testReport;
    }
}

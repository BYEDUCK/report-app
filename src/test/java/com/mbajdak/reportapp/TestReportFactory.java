package com.mbajdak.reportapp;

import com.mbajdak.reportapp.domain.Report;

public class TestReportFactory {
    public static Report getTestReport(String characterName, String planetName,
                                       Long filmId, String filmName,
                                       Long characterId, Long planetId, Integer reportId) {
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

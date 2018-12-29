package com.mbajdak.reportapp.service;

import com.mbajdak.reportapp.domain.Report;

import java.util.List;

public interface ReportService {
    List<Report> getReportsByCharacterPhraseAndPlanetName(String characterPhrase, String planetName);
    void deleteAll();
}

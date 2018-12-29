package com.mbajdak.reportapp.service;

import com.mbajdak.reportapp.domain.Report;
import com.mbajdak.reportapp.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private ReportRepository reportRepository;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public List<Report> getReportsByCharacterPhraseAndPlanetName(String characterPhrase, String planetName) {
        if (planetName == null || planetName.trim().equals(""))
            return new ArrayList<>();
        if (characterPhrase == null)
            characterPhrase = "";
        return reportRepository.findReportsByCharacterNameContainsAndPlanetNameEquals(characterPhrase, planetName);
    }

    @Override
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    @Override
    public void deleteAll() {
        reportRepository.deleteAll();
    }

    @Override
    public List<Report> saveAll(List<Report> reports) {
        if (reports.isEmpty())
            return new ArrayList<>();
        return reportRepository.saveAll(reports);
    }
}

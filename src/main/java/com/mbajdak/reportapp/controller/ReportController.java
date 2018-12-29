package com.mbajdak.reportapp.controller;

import com.mbajdak.reportapp.domain.Report;
import com.mbajdak.reportapp.domain.ReportResponse;
import com.mbajdak.reportapp.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {

    private ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/report/{characterPhrase}/{planetName}")
    public ReportResponse getReportsByCharacterPhraseAndPlanetName(
            @PathVariable(name = "characterPhrase") String characterPhrase,
            @PathVariable(name = "planetName") String planetName)
    {
        List<Report> reports = reportService.getReportsByCharacterPhraseAndPlanetName(characterPhrase, planetName);
        return new ReportResponse(reports, characterPhrase, planetName);
    }

    @GetMapping("/report")
    public ReportResponse getAllReports() {
        return new ReportResponse(
                reportService.getReportsByCharacterPhraseAndPlanetName(null ,null),
                "", ""
        );
    }

    @DeleteMapping("/report")
    public void deleteAll() {
        reportService.deleteAll();
    }
}

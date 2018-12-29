package com.mbajdak.reportapp.controller;

import com.mbajdak.reportapp.domain.Criteria;
import com.mbajdak.reportapp.domain.FilmDTO;
import com.mbajdak.reportapp.domain.PersonDTO;
import com.mbajdak.reportapp.domain.PlanetDTO;
import com.mbajdak.reportapp.domain.Report;
import com.mbajdak.reportapp.domain.ReportResponse;
import com.mbajdak.reportapp.service.FilmService;
import com.mbajdak.reportapp.service.PeopleService;
import com.mbajdak.reportapp.service.PlanetService;
import com.mbajdak.reportapp.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ReportController {

    private ReportService reportService;
    private PeopleService peopleService;
    private PlanetService planetService;
    private FilmService filmService;

    @Autowired
    public ReportController(ReportService reportService, PeopleService peopleService, PlanetService planetService, FilmService filmService) {
        this.reportService = reportService;
        this.peopleService = peopleService;
        this.planetService = planetService;
        this.filmService = filmService;
    }

    @GetMapping("/report/{characterPhrase}/{planetName}")
    public ReportResponse getReportsByCharacterPhraseAndPlanetName(
            @PathVariable(name = "characterPhrase") String characterPhrase,
            @PathVariable(name = "planetName") String planetName) {
        List<Report> reports = reportService.getReportsByCharacterPhraseAndPlanetName(characterPhrase, planetName);
        return new ReportResponse(reports, characterPhrase, planetName);
    }

    @GetMapping("/report")
    public ReportResponse getAllReports() {
        return new ReportResponse(
                reportService.getAllReports(),
                "", ""
        );
    }

    @DeleteMapping("/report")
    public void deleteAll() {
        reportService.deleteAll();
    }

    @PutMapping("/report")
    public List<Report> generateReports(@RequestBody Criteria searchCriteria) throws IOException {
        PlanetDTO planet = planetService.getPlanetForName(searchCriteria.getPlanetName());
        List<Report> reports = new ArrayList<>();
        if (planet != null) {
            List<PersonDTO> people = peopleService
                    .getPeopleContainingCharacterPhrase(searchCriteria.getCharacterPhrase());
            for (PersonDTO person : people) {
                if (person.getHomeworld().equals(planet.getUrl())) {
                    for (String filmURL : person.getFilms()) {
                        Report report = new Report();
                        report.setPlanetId(planet.getId());
                        report.setCharacterName(person.getName());
                        report.setCharacterId(person.getId());
                        report.setPlanetName(planet.getName());
                        FilmDTO film = new FilmDTO(null, filmURL);
                        film = filmService.getFilmById(film.getId());
                        report.setFilmId(film.getId());
                        report.setFilmName(film.getName());
                        reports.add(report);
                    }
                }
            }
            return reportService.saveAll(reports);
        }
        return reports;
    }
}

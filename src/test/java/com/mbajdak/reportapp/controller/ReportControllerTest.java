package com.mbajdak.reportapp.controller;

import com.mbajdak.reportapp.domain.Criteria;
import com.mbajdak.reportapp.domain.FilmDTO;
import com.mbajdak.reportapp.domain.PersonDTO;
import com.mbajdak.reportapp.domain.PlanetDTO;
import com.mbajdak.reportapp.domain.Report;
import com.mbajdak.reportapp.domain.ReportResponse;
import com.mbajdak.reportapp.repository.ReportRepository;
import com.mbajdak.reportapp.service.FilmService;
import com.mbajdak.reportapp.service.PeopleService;
import com.mbajdak.reportapp.service.PlanetService;
import com.mbajdak.reportapp.service.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.mbajdak.reportapp.TestReportFactory.getTestReport;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ReportControllerTest {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private PeopleService peopleService;

    @Mock
    private PlanetService planetService;

    @Mock
    private FilmService filmService;

    @InjectMocks
    private ReportServiceImpl reportService;

    private ReportController reportController;

    @BeforeEach
    void setUp() {
        reportController = new ReportController(reportService, peopleService, planetService, filmService);
    }

    @Test
    @DisplayName("Test get mapping without search parameters")
    void getMappingTest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
        mockMvc.perform(get("/report"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test get mapping with search parameters")
    void getMappingTest1() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
        mockMvc.perform(get("/report/{characterPhrase}/{planetName}", "test", "test"))
                .andExpect(status().isOk());
    }

    @Test
    void getReports() {
        Report testReport1 = getTestReport("Test11", "Planet1", 1,
                "Film Name", 1, 1, null);
        Report testReport2 = getTestReport("Test12", "Planet2", 1,
                "Film Name", 1, 1, null);
        Report testReport3 = getTestReport("Test13", "Planet1", 1,
                "Film Name", 1, 1, null);
        List<Report> reports = Arrays.asList(testReport1, testReport2, testReport3);
        String characterPhrase = "test";
        String planetName = "test";
        when(reportService.getReportsByCharacterPhraseAndPlanetName(characterPhrase, planetName)).thenReturn(reports);
        ReportResponse reportResponse = reportController.getReportsByCharacterPhraseAndPlanetName(characterPhrase, planetName);
        assertNotNull(reportResponse.getReports());
        assertNotNull(reportResponse.getCriteria());
        assertThat(reportResponse.getReports()).isEqualTo(reports);
        assertEquals(characterPhrase, reportResponse.getCriteria().getCharacterPhrase());
        assertEquals(planetName, reportResponse.getCriteria().getPlanetName());
    }

    @Test
    @Disabled
    void generateReportsTest() throws IOException {
        PlanetDTO planet = new PlanetDTO("test", "test/1/");
        PersonDTO person1 = new PersonDTO("test1name", "test1url/1/", "test1homeworld",
                Arrays.asList("test1a/1/", "test1b/2/", "test1c/3/"));
        PersonDTO person2 = new PersonDTO("test2name", "test2url/2/", "test2homeworld",
                Arrays.asList("test2a/4/", "test2b/5/", "test2c/6/"));
        FilmDTO film1a = new FilmDTO("1a", "test1a/1/");
        FilmDTO film1b = new FilmDTO("1b", "test1b/2/");
        FilmDTO film1c = new FilmDTO("1c", "test1c/3/");
        FilmDTO film2a = new FilmDTO("2a", "test2a/4/");
        FilmDTO film2b = new FilmDTO("2b", "test2b/5/");
        FilmDTO film2c = new FilmDTO("2c", "test2c/6/");
        when(planetService.getPlanetForName("test")).thenReturn(planet);
        when(peopleService.getPeopleContainingCharacterPhrase("test"))
                .thenReturn(Arrays.asList(person1, person2));
        when(filmService.getFilmById(1)).thenReturn(film1a);
        when(filmService.getFilmById(2)).thenReturn(film1b);
        when(filmService.getFilmById(3)).thenReturn(film1c);
        when(filmService.getFilmById(4)).thenReturn(film2a);
        when(filmService.getFilmById(5)).thenReturn(film2b);
        when(filmService.getFilmById(6)).thenReturn(film2c);

        Criteria criteria = new Criteria();
        criteria.setCharacterPhrase("test");
        criteria.setPlanetName("test");

    }

    @Test
    void deleteAll() {
    }
}
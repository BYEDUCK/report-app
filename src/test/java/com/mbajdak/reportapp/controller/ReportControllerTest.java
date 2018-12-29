package com.mbajdak.reportapp.controller;

import com.mbajdak.reportapp.domain.Report;
import com.mbajdak.reportapp.domain.ReportResponse;
import com.mbajdak.reportapp.repository.ReportRepository;
import com.mbajdak.reportapp.service.ReportServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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

    @InjectMocks
    private ReportServiceImpl reportService;

    private ReportController reportController;

    @BeforeEach
    void setUp() {
        reportController = new ReportController(reportService);
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
        Report testReport1 = getTestReport("Test11", "Planet1", 1L,
                "Film Name", 1L, 1L, null);
        Report testReport2 = getTestReport("Test12", "Planet2", 1L,
                "Film Name", 1L, 1L, null);
        Report testReport3 = getTestReport("Test13", "Planet1", 1L,
                "Film Name", 1L, 1L, null);
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
    void deleteAll() {
    }
}
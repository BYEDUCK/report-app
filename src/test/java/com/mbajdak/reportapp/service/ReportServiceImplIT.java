package com.mbajdak.reportapp.service;

import com.mbajdak.reportapp.domain.Report;
import com.mbajdak.reportapp.repository.ReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.mbajdak.reportapp.TestReportFactory.getTestReport;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@DataJpaTest
class ReportServiceImplIT {

    @Autowired
    private ReportRepository reportRepository;

    private ReportService reportService;

    @BeforeEach
    void setUp() {
        reportService = new ReportServiceImpl(reportRepository);
    }

    @Test
    @DisplayName("Test getting reports with null characterPhrase")
    void getReportsByCharacterPhraseAndPlanetName() {
        Report testReport1 = getTestReport("Test11", "Planet", 1L,
                "Film Name", 1L, 1L, null);
        Report testReport2 = getTestReport("Test12", "Planet", 1L,
                "Film Name", 1L, 1L, null);
        Report testReport3 = getTestReport("Test22", "Planet", 1L,
                "Film Name", 1L, 1L, null);

        Report saved1 = reportRepository.save(testReport1);
        Report saved2 = reportRepository.save(testReport2);
        Report saved3 = reportRepository.save(testReport3);

        List<Report> found = reportService.getReportsByCharacterPhraseAndPlanetName(null, "Planet");
        assertNotNull(found);
        assertEquals(3, found.size());
        assertThat(found).contains(saved1, saved2, saved3);
    }
}
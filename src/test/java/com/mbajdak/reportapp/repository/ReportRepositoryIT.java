package com.mbajdak.reportapp.repository;

import com.mbajdak.reportapp.domain.Report;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.mbajdak.reportapp.TestReportFactory.getTestReport;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReportRepositoryIT {

    @Autowired
    ReportRepository reportRepository;

    @Test
    @DisplayName("Test empty db returns empty list")
    public void emptyTableTest() {
        List<Report> found = reportRepository.findAll();
        assertEquals(found.size(), 0);
    }

    @Test
    @DisplayName("Test insertion of a report")
    public void insertionTest() {
        // given
        Report testReport = getTestReport("Test Character Name", "Test Planet Name", 1L,
                "Test Film Name", 1L, 1L, null);
        // when
        Report saved = reportRepository.save(testReport);
        // then
        assertNotNull(saved.getId());
        testReport.setId(saved.getId());
        List<Report> found = reportRepository.findAll();
        assertEquals(1, found.size());
        assertEquals(testReport, found.get(0));
    }

    @Test
    @DisplayName("Test finding by character phrase and same planet name")
    public void findReportsByCharacterNameContainsAndPlanetNameEqualsTest() {
        // given
        Report testReport1 = getTestReport("Test11", "Planet", 1L,
                "Film Name", 1L, 1L, null);
        Report testReport2 = getTestReport("Test12", "Planet", 1L,
                "Film Name", 1L, 1L, null);
        Report testReport3 = getTestReport("Test22", "Planet", 1L,
                "Film Name", 1L, 1L, null);
        // when
        Report saved1 = reportRepository.save(testReport1);
        Report saved2 = reportRepository.save(testReport2);
        Report saved3 = reportRepository.save(testReport3);
        // then
        List<Report> found = reportRepository.findReportsByCharacterNameContainsAndPlanetNameEquals(
                "1", "Planet");
        assertEquals(2, found.size());
        assertThat(found).contains(saved1, saved2);
    }

    @Test
    @DisplayName("Test finding by character phrase and different planet names")
    public void findReportsByCharacterNameContainsAndPlanetNameEqualsTest1() {
        // given
        Report testReport1 = getTestReport("Test11", "Planet1", 1L,
                "Film Name", 1L, 1L, null);
        Report testReport2 = getTestReport("Test12", "Planet2", 1L,
                "Film Name", 1L, 1L, null);
        Report testReport3 = getTestReport("Test13", "Planet1", 1L,
                "Film Name", 1L, 1L, null);
        // when
        Report saved1 = reportRepository.save(testReport1);
        Report saved2 = reportRepository.save(testReport2);
        Report saved3 = reportRepository.save(testReport3);
        // then
        List<Report> found = reportRepository.findReportsByCharacterNameContainsAndPlanetNameEquals(
                "1", "Planet1");
        assertEquals(2, found.size());
        assertThat(found).contains(saved1, saved3);
    }
}
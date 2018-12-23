package com.mbajdak.reportapp.repository;

import com.mbajdak.reportapp.domain.Report;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReportRepositoryTest {

    @Autowired
    ReportRepository reportRepository;

    @Test
    public void emptyTableTest() {
        List<Report> found = reportRepository.findAll();
        assertEquals(found.size(), 0);
    }

    @Test
    public void insertionTest() {
        Report testReport = new Report();
        testReport.setCharacterName("Test Character Name");
        testReport.setPlanetName("Test Planet Name");
        testReport.setFilmId(1L);
        testReport.setFilmName("Test Film Name");
        testReport.setCharacterId(1L);
        testReport.setPlanetId(1L);

        Report saved = reportRepository.save(testReport);
        assertNotNull(saved.getId());
        testReport.setId(saved.getId());
        List<Report> found = reportRepository.findAll();
        assertEquals(1, found.size());
        assertEquals(testReport, found.get(0));
    }
}
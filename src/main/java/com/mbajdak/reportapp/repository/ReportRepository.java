package com.mbajdak.reportapp.repository;

import com.mbajdak.reportapp.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    List<Report> findReportsByCharacterNameContainsAndPlanetNameEquals(String characterPhrase, String planetName);
}

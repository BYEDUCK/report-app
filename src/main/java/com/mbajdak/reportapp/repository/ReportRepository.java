package com.mbajdak.reportapp.repository;

import com.mbajdak.reportapp.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
    Optional<Report> findReportsByCharacterNameContainsAndPlanetNameEquals(String characterPhrase, String planetName);
}

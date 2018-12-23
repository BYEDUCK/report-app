package com.mbajdak.reportapp.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "report")
public class Report {

    @EmbeddedId
    private ReportPK reportPK;

    @Column(name = "film_id")
    private Long filmId;

    @Column(name = "film_name")
    private String filmName;

    @Column(name = "character_id")
    private Long characterId;

    @Column(name = "planet_id")
    private Long planetId;
}

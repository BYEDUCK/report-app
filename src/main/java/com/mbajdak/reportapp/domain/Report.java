package com.mbajdak.reportapp.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "report")
public class Report {

    @Id
    @Column(name = "report_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "character_name")
    private String characterName;

    @Column(name = "planet_name")
    private String planetName;

    @Column(name = "film_id")
    private Long filmId;

    @Column(name = "film_name")
    private String filmName;

    @Column(name = "character_id")
    private Long characterId;

    @Column(name = "planet_id")
    private Long planetId;
}

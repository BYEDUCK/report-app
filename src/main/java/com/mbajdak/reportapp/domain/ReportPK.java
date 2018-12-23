package com.mbajdak.reportapp.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ReportPK implements Serializable {

    @Column(name = "character_name")
    private String characterName;

    @Column(name = "planet_name")
    private String planetName;

}

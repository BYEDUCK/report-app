package com.mbajdak.reportapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class PersonDTO extends BaseDTO {

    private String homeworld;
    private List<String> films;

    public PersonDTO(String name, String url) {
        super(name, url);
    }

    public PersonDTO(String name, String url, String homeworld, List<String> films) {
        super(name, url);
        this.homeworld = homeworld;
        this.films = films;
    }

    public static PersonDTO fromPerson(Person person) {
        return new PersonDTO(person.getName(), person.getUrl(), person.getHomeworld(), person.getFilms());
    }
}

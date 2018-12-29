package com.mbajdak.reportapp.service;

import com.mbajdak.reportapp.domain.PersonDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
class PeopleServiceImplIT {

    @Autowired
    private PeopleService peopleService;

    @Test
    void getPeopleContainingCharacterPhrase() throws IOException {
        String characterPhrase = "Lu";
        List<PersonDTO> people = peopleService.getPeopleContainingCharacterPhrase(characterPhrase);
        assertNotNull(people);
        assertEquals(2, people.size());
        List<PersonDTO> expected = Arrays.asList(
                new PersonDTO("Luke Skywalker", "https://swapi.co/api/people/1/",
                        "https://swapi.co/api/planets/1/",
                        Arrays.asList(
                                "https://swapi.co/api/films/2/",
                                "https://swapi.co/api/films/6/",
                                "https://swapi.co/api/films/3/",
                                "https://swapi.co/api/films/1/",
                                "https://swapi.co/api/films/7/"
                        )),
                new PersonDTO("Luminara Unduli", "https://swapi.co/api/people/64/",
                        "https://swapi.co/api/planets/51/",
                        Arrays.asList(
                                "https://swapi.co/api/films/5/",
                                "https://swapi.co/api/films/6/"
                        ))
        );
        assertThat(people).isEqualTo(expected);
    }
}
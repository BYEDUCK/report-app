package com.mbajdak.reportapp.service;

import com.mbajdak.reportapp.domain.FilmDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FilmServiceImplIT {

    @Autowired
    private FilmService filmService;

    @Test
    void getFilmById() throws IOException {
        int id = 1;
        FilmDTO film = filmService.getFilmById(id);
        assertNotNull(film);
        FilmDTO expected = new FilmDTO("A New Hope", "https://swapi.co/api/films/1/");
        assertEquals(expected, film);
    }
}
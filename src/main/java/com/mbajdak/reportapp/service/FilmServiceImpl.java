package com.mbajdak.reportapp.service;

import com.mbajdak.reportapp.domain.Film;
import com.mbajdak.reportapp.domain.FilmDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Service
public class FilmServiceImpl extends ExternalApiCallServiceBase implements FilmService {

    private final static String EXTERNAL_SERVICE_URL = "https://swapi.co/api/films/";


    @Autowired
    protected FilmServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public FilmDTO getFilmById(Integer id) throws IOException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(EXTERNAL_SERVICE_URL + id);
        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        Film film = getObjectFromJsonResponsePlain(Film.class, response);
        return FilmDTO.fromFilm(film);
    }
}

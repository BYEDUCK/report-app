package com.mbajdak.reportapp.service;

import com.mbajdak.reportapp.domain.FilmDTO;

import java.io.IOException;

public interface FilmService {
    FilmDTO getFilmById(Integer id) throws IOException;
}

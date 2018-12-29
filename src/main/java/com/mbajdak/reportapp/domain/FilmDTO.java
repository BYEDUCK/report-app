package com.mbajdak.reportapp.domain;

public class FilmDTO extends BaseDTO {
    public FilmDTO(String name, String url) {
        super(name, url);
    }

    public static FilmDTO fromFilm(Film film) {
        return new FilmDTO(film.getTitle(), film.getUrl());
    }
}

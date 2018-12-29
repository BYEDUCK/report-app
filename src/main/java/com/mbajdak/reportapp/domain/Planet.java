package com.mbajdak.reportapp.domain;

import lombok.Data;

@Data
public class Planet {
    private String name;
    private Integer id;

    public Planet(String name, String url) {
        this.name = name;
        if (url.endsWith("/"))
            id = Integer.parseInt(String.valueOf(url.charAt(url.length() - 2)));
        else
            id = Integer.parseInt(String.valueOf(url.charAt(url.length() - 1)));
    }
}

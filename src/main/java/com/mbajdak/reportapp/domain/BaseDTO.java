package com.mbajdak.reportapp.domain;

import lombok.Data;

@Data
public abstract class BaseDTO {
    String name;
    Integer id;
    String url;

    public BaseDTO(String name, String url) {
        this.url = url;
        this.name = name;
        if (url.endsWith("/"))
            id = Integer.parseInt(String.valueOf(url.charAt(url.length() - 2)));
        else
            id = Integer.parseInt(String.valueOf(url.charAt(url.length() - 1)));
    }
}

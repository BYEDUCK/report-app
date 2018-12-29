package com.mbajdak.reportapp.domain;

import lombok.Data;

@Data
public abstract class BaseEntity {
    String name;
    Integer id;

    public BaseEntity(String name, String url) {
        this.name = name;
        if (url.endsWith("/"))
            id = Integer.parseInt(String.valueOf(url.charAt(url.length() - 2)));
        else
            id = Integer.parseInt(String.valueOf(url.charAt(url.length() - 1)));
    }
}

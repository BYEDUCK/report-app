CREATE TABLE report (film_id NUMBER, film_name VARCHAR,
                     character_id NUMBER, character_name VARCHAR,
                     planet_id NUMBER, planet_name VARCHAR,
                     PRIMARY KEY (character_name, planet_name));
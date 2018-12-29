package com.mbajdak.reportapp.service;

import com.mbajdak.reportapp.domain.PersonDTO;

import java.io.IOException;
import java.util.List;

public interface PeopleService {
    List<PersonDTO> getPeopleContainingCharacterPhrase(String characterPhrase) throws IOException;
}

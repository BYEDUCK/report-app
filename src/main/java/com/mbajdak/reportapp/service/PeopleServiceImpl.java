package com.mbajdak.reportapp.service;

import com.mbajdak.reportapp.domain.Person;
import com.mbajdak.reportapp.domain.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeopleServiceImpl extends ExternalApiCallServiceBase implements PeopleService {

    private final static String EXTERNAL_SERVICE_URL = "https://swapi.co/api/people/";


    @Autowired
    protected PeopleServiceImpl(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public List<PersonDTO> getPeopleContainingCharacterPhrase(String characterPhrase) throws IOException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(EXTERNAL_SERVICE_URL)
                .queryParam("search", characterPhrase);
        ResponseEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        List<Person> people = getObjectsFromJsonResponse(Person.class, response);
        return people.stream().map(PersonDTO::fromPerson).collect(Collectors.toList());
    }
}

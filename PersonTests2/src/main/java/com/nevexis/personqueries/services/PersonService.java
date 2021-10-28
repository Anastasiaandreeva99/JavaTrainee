package com.nevexis.personqueries.services;

import org.springframework.stereotype.Service;

@Service
public class PersonService {

    public String findEntity(String id) {
        return String.format("Retrieved entity with id: %s", id);
    }


}

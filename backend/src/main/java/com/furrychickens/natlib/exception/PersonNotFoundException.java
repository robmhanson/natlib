package com.furrychickens.natlib.exception;

public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(Long personId) {
        super("Person with id " + personId + " does not exist");
    }
}

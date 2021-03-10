package com.test.league.football.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName) {
        super(resourceName + " resource Not Authorized or Not Found");
    }
}
